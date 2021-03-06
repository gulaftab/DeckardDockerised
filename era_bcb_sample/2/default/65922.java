import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A program starter that reads a classpath and runnable type from manifest-file. The
 * comma-separated classpath is expanded (each entry that is a directory is scanned
 * for jar-files) and the runnable invoked with the resulting classpath.
 * 
 * An applicable manifest file should roughly look like this example: META-INF/MANIFEST.MF
 * 
 * <pre>
 *   Main-Class: Run
 *   Run-Classpath: ./lib
 *   Run-Runnable: some.package.and.Runnable </pre>
 *   
 * The manifest entry Main-Class makes sure that Run can be started via
 * <pre>
 *   java -jar thejar.jar
 * </pre>
 */
public class Run {

    public static final String MANIFEST = "META-INF/MANIFEST.MF", CLASSPATH = "Run-Classpath", MAIN_CLASS = "Run-Class";

    private static Logger logger = Logger.getLogger(Run.class.getName());

    /**
   * Startup runnable with dynamically constructed classpath
   */
    public static void main(String[] args) {
        try {
            cd(Run.class);
            Manifest mf = getManifest();
            URL[] classpath = getClasspath(mf);
            ClassLoader cl = new URLClassLoader(classpath);
            Thread.currentThread().setContextClassLoader(cl);
            Class clazz = cl.loadClass(getClass(mf));
            Method method = clazz.getMethod("main", new Class[] { String[].class });
            method.invoke(null, new Object[] { args });
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    /**
   * Try to change into the directory of jar file that contains given class.
   * @param  clazz  class to get containing jar file for
   * @return success or not 
   */
    private static boolean cd(Class clazz) {
        try {
            JarURLConnection jarCon = (JarURLConnection) getClassURL(clazz).openConnection();
            URL jarUrl = jarCon.getJarFileURL();
            File jarFile = new File(URLDecoder.decode(jarUrl.getPath(), "UTF-8"));
            File jarDir = jarFile.getParentFile();
            System.setProperty("user.dir", jarDir.getAbsolutePath());
            logger.log(Level.FINE, "cd'd into " + jarDir + " with jar containing " + clazz);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Couldn't cd into directory with jar containing " + clazz, ex);
            return false;
        }
    }

    /**
   * Get URL of the given class.
   * 
   * @param  clazz  class to get URL for
   * @return the URL this class was loaded from
   */
    private static URL getClassURL(Class clazz) {
        String resourceName = "/" + clazz.getName().replace('.', '/') + ".class";
        return clazz.getResource(resourceName);
    }

    /**
   * Get main class from manifest file information
   */
    private static String getClass(Manifest mf) {
        String clazz = mf.getMainAttributes().getValue(MAIN_CLASS);
        if (clazz == null || clazz.length() == 0) {
            throw new Error("No " + MAIN_CLASS + " defined in " + MANIFEST);
        }
        return clazz;
    }

    /**
   * Assemble classpath from manifest file information (optional)
   */
    private static URL[] getClasspath(Manifest mf) throws MalformedURLException {
        String classpath = mf.getMainAttributes().getValue(CLASSPATH);
        if (classpath == null) classpath = "";
        StringTokenizer tokens = new StringTokenizer(classpath, ",", false);
        List result = new ArrayList();
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            File file = new File(token).getAbsoluteFile();
            if (!file.exists()) {
                logger.log(Level.WARNING, CLASSPATH + " contains " + token + " but file/directory " + file.getAbsolutePath() + " doesn't exist");
                continue;
            }
            buildClasspath(file, result);
        }
        return (URL[]) result.toArray(new URL[result.size()]);
    }

    private static void buildClasspath(File file, List result) throws MalformedURLException {
        if (!file.isDirectory() && file.getName().endsWith(".jar")) {
            result.add(file.toURL());
            return;
        }
        File[] files = file.listFiles();
        if (files != null) for (int i = 0; i < files.length; i++) buildClasspath(files[i], result);
    }

    /**
   * Get our manifest file. Normally all (parent) classloaders of a class do provide
   * resources and the enumeration returned on lookup of manifest.mf will start
   * with the topmost classloader's resources. 
   * We're inverting that order to make sure we're consulting the manifest file in 
   * the same jar as this class if available.
   */
    private static Manifest getManifest() throws IOException {
        Stack manifests = new Stack();
        for (Enumeration e = Run.class.getClassLoader().getResources(MANIFEST); e.hasMoreElements(); ) manifests.add(e.nextElement());
        while (!manifests.isEmpty()) {
            URL url = (URL) manifests.pop();
            InputStream in = url.openStream();
            Manifest mf = new Manifest(in);
            in.close();
            if (mf.getMainAttributes().getValue(MAIN_CLASS) != null) return mf;
        }
        throw new Error("No " + MANIFEST + " with " + MAIN_CLASS + " found");
    }
}
