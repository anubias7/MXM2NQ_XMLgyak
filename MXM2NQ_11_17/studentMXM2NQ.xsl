<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"></xsl:output>
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>Kormos Máté</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Vezetéknév</th>
                        <th>Keresztnév</th>
                        <th>Becenév</th>
                        <th>Kor</th>
                    </tr>
                    <xsl:for-each select="class/student">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="vezeteknev" />
                            </td>
                            <td>
                                <xsl:value-of select="keresztnev" />
                            </td>
                            <td>
                                <xsl:value-of select="becenev" />
                            </td>
                            <td>
                                <xsl:value-of select="kor" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>