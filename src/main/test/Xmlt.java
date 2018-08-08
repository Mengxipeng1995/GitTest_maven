import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xmlt {

    public static void main(String[] args) throws DocumentException {
        handleXmlFile("E:\\化工\\xml",new ArrayList());
    }

    public static void handleXmlFile(String path, List list) throws DocumentException {

        File[] listFiles = new File(path).listFiles();

        for (int i = 0; i < listFiles.length; i++) {

            if (listFiles[i].isDirectory()) {
                handleXmlFile(listFiles[i].getPath(), list);
            } else {
                String fileName = listFiles[i].getName();
                String filePath = listFiles[i].getPath();

                if (fileName.contains(".jpg") && fileName.endsWith(".jpg")) {
                    break;
                }
                if (fileName.contains(".bak") && fileName.endsWith(".bak")) {
                    break;
                }

                if (!filePath.contains("__MACOSX")) {
                    if (fileName.contains(".xml") && fileName.endsWith(".xml")) {
                        String xmlFilePath = listFiles[i].getPath();
                        Map map = new HashMap();
                        map.put("book", "http://docbook.org/ns/docbook");

                        Map temp = new HashMap();

                        SAXReader saxReader = new SAXReader();
                        saxReader.getDocumentFactory().setXPathNamespaceURIs(map);

                            Document document = saxReader.read(new File(xmlFilePath));
                            /*获取标题*/
                            String book_name = document.selectSingleNode("//book:title").getText();
                            temp.put("book_name", book_name.replaceAll(" ", ""));
                            /*获取ISBN*/
                            String regEx = "[ISBN ]";
                            Pattern p = Pattern.compile(regEx);
                            String isbn_full = document.selectSingleNode("//book:biblioid[@class='isbn']").getText();
                            Matcher m = p.matcher(isbn_full);
                            temp.put("isbn_full", m.replaceAll("").trim());
                            /*获取短ISBN*/
                            String isbn = document.selectSingleNode("//book:releaseinfo[@role='shortisbn']").getText();
                            temp.put("isbn", isbn);
                            /*获取出版社*/
                            String publisher = document.selectSingleNode("//book:publisher/book:publishername").getText();
                            temp.put("publisher", publisher);
                            /*获取CIP*/
                            List<Element> cipList = document.selectNodes("//book:releaseinfo[@role='CIP']/book:alt");
                            String cip = "";
                            temp.put("cip", cip);
                            /*获取作者*/
                            String regExAuthor = "[。? ]";
                            Pattern patternAuthor = Pattern.compile(regExAuthor);
                            List<Element> authorList = (document.selectNodes("//book:authorgroup/book:author[@role='主编']/book:personname").size() != 0 ?
                                    document.selectNodes("//book:authorgroup/book:author[@role='主编']/book:personname")
                                    : document.selectNodes("//book:authorgroup/book:author[1]/book:personname"));
                            String author = "";
                            temp.put("author", author);
                            /*获取cipnum*/
                            String cip_num = document.selectSingleNode("//book:biblioid[@class='other' and @otherclass='CIP' or @otherclass='cip']").getText();
                            temp.put("cip_num", cip_num);
                            /*获取中图分类号*/
                            String clc = document.selectSingleNode("//book:releaseinfo[@role='ztfclasscode']").getText();
                            temp.put("clc", clc);
                            /*获取版次*/
                            String version = document.selectSingleNode("//book:revision/book:revnumber").getText();
                            temp.put("version", version);
                            /*获取出版时间*/
                            String pub_date = document.selectSingleNode("//book:revision/book:date").getText();
                            temp.put("pub_date", pub_date);
                            /*获取价格*/
                            String price = document.selectSingleNode("//book:releaseinfo[@role='price']").getText();
                            temp.put("price", price);
                            /*获取关键字*/
                            String regExkw = "[①②③④⑤⑥⑦⑧⑨⑩]";
                            Pattern pkw = Pattern.compile(regExkw);
                            List<Element> keywordList = document.selectNodes("//book:keywordset/book:keyword");
                        }
                    }
                }
            }
        }

}