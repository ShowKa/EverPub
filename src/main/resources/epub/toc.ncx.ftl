<?xml version='1.0' encoding='utf-8'?>
<ncx xmlns="http://www.daisy.org/z3986/2005/ncx/" version="2005-1" >
    <head>
        <meta content="9999999999999" name="dtb:uid" />
        <meta content="1" name="dtb:depth" />
        <meta content="calibre (0.8.68)" name="dtb:generator" />
        <meta content="0" name="dtb:totalPageCount" />
        <meta content="0" name="dtb:maxPageNumber" />
    </head>
    <docTitle>
        <text>月面ラジオ</text>
    </docTitle>
    <navMap>
        <#list chapters as chapter>
        <navPoint id="${chapter.id}" playOrder="${chapter?index}">
            <navLabel>
                <text>${chapter.title}</text>
            </navLabel>
            <content src="text/${chapter.fileName}" />
        </navPoint>
        </#list>
    </navMap>
</ncx>
