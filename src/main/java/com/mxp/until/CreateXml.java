package com.mxp.until;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

class CreateXml {

    /**
     * DOM方式创建xml文件
     * @param file 文件
     * @throws Exception
     */
    public static void DOMcreate(File file)throws Exception{
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.newDocument();
        document.setXmlStandalone(true);
        Element root=document.createElement("tb_student");
        //for (int i = 0; i < 3; i++) {
            Element student=document.createElement("student"),
                    name=document.createElement("name"),
                    age=document.createElement("age"),
                    grade=document.createElement("grade");
            student.setAttribute("id", "1");
            student.setAttribute("bq","2");


            name.setTextContent("张");
            age.setTextContent("");
            grade.setTextContent("");
            student.appendChild(name);
            student.appendChild(age);
            student.appendChild(grade);
            root.appendChild(student);
      //  }
        document.appendChild(root);
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(file));
    }

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\a.xml");
        DOMcreate(file);
    }
}