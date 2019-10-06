<?xml version="1.0" encoding="UTF-8"?>
<package xmlns="http://www.idpf.org/2007/opf" unique-identifier="isbn" version="3.0" xml:lang="ja" prefix="layout: http://xmlns.sony.net/e-book/prs/layoutoptions/ prism: http://prismstandard.org/namespaces/basic/2.1/">
    <metadata xmlns:dc="http://purl.org/dc/elements/1.1/">
        <dc:identifier id="isbn">999999999</dc:identifier>
        <dc:title id="title">月面ラジオ</dc:title>
        <dc:creator id="creator">かなもと</dc:creator>
        <dc:publisher id="publisher">com.showka</dc:publisher>
        <dc:language>ja</dc:language>
        <dc:description id="description">月まで会いにいくよ。</dc:description>
        <dc:rights>Copyright (c) 2019 Kanamoto</dc:rights>
        <meta property="prism:volume">2</meta>
        <meta property="prism:number">1</meta>
        <meta property="dcterms:modified">2012-07-03T08:00:00Z</meta>
        <meta property="dcterms:creator" id="AuthorA">かなもと</meta>
        <meta property="prism:publicationName" id="publicationName">月面ラジオ</meta>
        <meta refines="#title" property="file-as">ゲツメンラジオ</meta>
        <meta refines="#creator" property="file-as">かなもと</meta>
        <meta refines="#AuthorA" property="file-as">かなもと</meta>
        <meta refines="#publisher" property="file-as">com.showka</meta>
        <meta refines="#publicationName" property="file-as">月面ラジオ</meta>
        <meta refines="#isbn" property="identifier-type" scheme="onix:codelist5">15</meta>
        <meta refines="#creator" property="role" scheme="marc:relators" id="role">aut</meta>
        <dc:identifier id="pub-id">urn:uuid:99999999-9999-9999-9999-999999999999</dc:identifier>
        <meta name="cover" content="cover_top"/>
    </metadata>
    <manifest>
        <!-- images -->
        <item href="images/cover.jpeg" id="cover" media-type="image/jpeg"/>
        <!-- styles -->
        <item id="picture" href="styles/pictureP4867TMR.css" media-type="text/css"/>
        <item id="common" href="styles/common8616PTMR.css" media-type="text/css"/>
        <item id="vertical" href="styles/vertical5648PTMR.css" media-type="text/css"/>
        <item id="horizontal" href="styles/horizontal6460PTMR.css" media-type="text/css"/>
        <item id="next_common" href="styles/next_commonPTMR3770.css" media-type="text/css"/>
        <item id="next_vertical" href="styles/next_verticalPT3679MR.css" media-type="text/css"/>
        <item id="next_horizontal" href="styles/next_horizontalP1433TMR.css" media-type="text/css"/>
        <!-- texts -->
        <item href="text/titlepage.xhtml" id="titlepage" media-type="application/xhtml+xml"/>

        <#list chapters as chapter>
        <item href="text/${chapter.fileName}" id="${chapter.id}" media-type="application/xhtml+xml"/>
        </#list>

        <!-- table of content -->
        <item href="toc.ncx" media-type="application/x-dtbncx+xml" id="ncx"/>
	</manifest>
    <spine page-progression-direction="rtl" toc="ncx">
        <itemref idref="titlepage" />

        <#list chapters as chapter>
        <itemref idref="${chapter.id}" />
        </#list>

    </spine>
    <guide>
        <reference href="titlepage.xhtml" type="cover" title="Cover"/>
    </guide>
</package>
