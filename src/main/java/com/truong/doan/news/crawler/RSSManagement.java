package com.truong.doan.news.crawler;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class RSSManagement {
    public final static String OUT_DIR = "D:\\deploy";
    public final static String JAVAFILENAME = "RSSTemplate";
    public final static String SOURCE =
            "package crawler;\n" +
                    "\n" +
                    "import com.truong.doan.news.common.StringUtility;\n" +
                    "import org.jsoup.Jsoup;\n" +
                    "import org.jsoup.nodes.Document;\n" +
                    "import org.jsoup.nodes.Element;\n" +
                    "import org.jsoup.select.Elements;\n" +
                    "\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.ArrayList;\n" +
                    "import java.util.Date;\n" +
                    "import java.util.List;\n" +
                    "\n" +
                    "public class RSSTemplate{\n" +
                    "//    public final static String LINK_RSS = \"https://baotintuc.vn/thoi-su.rss\";//\"https://tuoitre.vn/rss/thoi-su.rss\";\n" +
                    "//    public final static String REGEX_FORMAT_DATETIME = \"EEE, dd MMM yyyy HH:mm:ss 'GMT+7'\";\n" +
                    "\n" +
                    "    /**\n" +
                    "     *\n" +
                    "     */\n" +
                    "    private String linkRSS;\n" +
                    "    private String regexFormatDatetime;\n" +
                    "\n" +
                    "    public RSSTemplate(String linkRSS, String regexFormatDatetime) {\n" +
                    "        this.linkRSS = linkRSS;\n" +
                    "        this.regexFormatDatetime = regexFormatDatetime.equals(\"\")\n" +
                    "                ? StringUtility.REGEX_FORMAT_DATETIME\n" +
                    "                : regexFormatDatetime;\n" +
                    "    }\n" +
                    "\n" +
                    "    public List<String> crawl() {\n" +
                    "        List<String> listNews=null;\n" +
                    "\n" +
                    "        try {\n" +
                    "            listNews=new ArrayList<>();\n" +
                    "            Document doc = Jsoup.connect(linkRSS).get();\n" +
                    "            Elements items = doc.select(\"item\");\n" +
                    "\n" +
                    "            for (Element item : items) {\n" +
                    "                String title = item.select(\"title\").text();\n" +
                    "                String link = item.select(\"link\").text();\n" +
                    "                String pubDate = item.select(\"pubDate\").text();\n" +
                    "\n" +
                    "                String descriptionElement = item.select(\"description\")\n" +
                    "                        .text();\n" +
                    "                String image = StringUtility.getOneTagElement(descriptionElement);\n" +
                    "                image = fixImageSource(image);\n" +
                    "\n" +
                    "                Date publishDate = StringUtility.formatDate(pubDate, this.regexFormatDatetime);\n" +
                    "                listNews.add(image);" +
                    "            }\n" +
                    "\n" +
                    "            return listNews;\n" +
                    "\n" +
                    "        } catch (IOException e) {\n" +
                    "            System.out.println(e.getMessage());\n" +
                    "            return listNews;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * Administrator can change this line of code inside this function\n" +
                    "     *\n" +
                    "     * @param imageSource\n" +
                    "     * @return\n" +
                    "     */\n" +
                    "    public String fixImageSource(String imageSource) {\n" +
                    "        return imageSource.replaceAll(\"zoom/80_50/\", \"\");\n" +
                    "    }\n" +
                    "}\n";

    public static class MyJavaFile extends SimpleJavaFileObject {
        private String source;

        public MyJavaFile(String className, String source) {
            super(URI.create("string:///" + className + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.source = source;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException {
            return source;
        }
    }


    public static JavaFileObject getJavaFileObject(String source) {
        StringBuilder stringBuilder = new StringBuilder(source);
        JavaFileObject javaFileObject = null;
        try {
            javaFileObject = new MyJavaFile(JAVAFILENAME, stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return javaFileObject;
    }


    public static void compile(Iterable<? extends JavaFileObject> files) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager =
                javaCompiler.getStandardFileManager(
                        null, Locale.ENGLISH, null
                );
        Iterable options = Arrays.asList("-d", OUT_DIR);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(
                null,
                standardJavaFileManager,
                null,
                options,
                null,
                files
        );

        Boolean isCompleted = false;
        try {
            isCompleted = task.call();
        } catch (Exception e) {
            System.out.println("Error -------------------- \n" + e.getMessage());
        }

        if (isCompleted)
            System.out.println("Compile success");
    }


    public static void runIt() {
        File file = new File(OUT_DIR);
        try {
            // Convert File to a URL
            URL url = file.toURL(); // file:/classes/demo
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader loader = new URLClassLoader(urls);

            // Load in the class; Class.childclass should be located in
            // the directory file:/class/demo/
            Class thisClass = loader.loadClass("crawler.RSSTemplate");

            Class params[] = {};
            Object paramsObj[] = {};
            Constructor constructor = thisClass.getDeclaredConstructor(new Class[]{String.class, String.class});  //thisClass.newInstance();
            Object instance = constructor.newInstance(new Object[]{"https://tuoitre.vn/rss/thoi-su.rss", ""});
            Method thisMethod = thisClass.getDeclaredMethod("crawl", params);

            // run the testAdd() method on the instance:
            List<String> listNews = (List<String>) thisMethod.invoke(instance, paramsObj);
            System.out.println("My list crawl");
            for(String news:listNews)
                System.out.println(news);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        JavaFileObject javaFileObject = getJavaFileObject(SOURCE);
        Iterable<? extends JavaFileObject> files = Arrays.asList(javaFileObject);

        compile(files);

        runIt();
    }
}