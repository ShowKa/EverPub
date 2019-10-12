package com.showka.everpub.publish.pdf

import org.apache.fop.apps.FopFactory
import org.apache.fop.apps.MimeConstants
import java.io.File
import javax.xml.transform.TransformerFactory
import javax.xml.transform.sax.SAXResult
import javax.xml.transform.stream.StreamSource


fun main() {
    // src dest
    val inputPath = "/Users/kanemotoshouta/Desktop/月面ラジオ/OEBPS/text/empty.xhtml"
    val outputPath = "/Users/kanemotoshouta/Desktop/a.pdf"
    val xsltPath = "/Users/kanemotoshouta/Desktop/template.xsl"
    //
    val xsltFile = File(xsltPath)
    val xmlSource = StreamSource(File(inputPath))
    val fopFactory = FopFactory.newInstance()
    val foUserAgent = fopFactory.newFOUserAgent()
    val out = File(outputPath).outputStream()
    // fop
    val fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out)
    val factory = TransformerFactory.newInstance()
    val transformer = factory.newTransformer(StreamSource(xsltFile))
    val res = SAXResult(fop.defaultHandler)
    transformer.transform(xmlSource, res)
}