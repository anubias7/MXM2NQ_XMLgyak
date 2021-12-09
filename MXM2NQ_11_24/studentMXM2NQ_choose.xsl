<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>Hallgatók choose</h2>
                <table border="1" cellpadding="5px">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Vezetéknév</th>
                        <th>Keresztnév</th>
                        <th>Becenév</th>
                        <th>Fizetés</th>
                        <th>Fokozat</th>
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
                                <xsl:value-of select="fizetes" />
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="fizetes &lt; 200000">Low</xsl:when>
                                    <xsl:when test="fizetes &lt; 300000">Medium</xsl:when>
                                    <xsl:otherwise>High</xsl:otherwise>
                                </xsl:choose>
                            </td>

                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>