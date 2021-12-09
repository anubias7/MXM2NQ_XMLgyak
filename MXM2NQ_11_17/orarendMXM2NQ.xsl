<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"></xsl:output>

    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>Kormos Máté</h2>
                <table border="1" cellpadding="5px">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Type</th>
                        <th>Subject</th>
                        <th colspan="3">Date</th>
                        <th>Place</th>
                        <th>Teacher</th>
                        <th>Faculty</th>
                    </tr>
                    <xsl:for-each select="lectures/lecture">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="@type" />
                            </td>
                            <td>
                                <xsl:value-of select="subject" />
                            </td>
                            <td>
                                <xsl:value-of select="date/day" />
                            </td>
                            <td>
                                <xsl:value-of select="date/start" />
                            </td>
                            <td>
                                <xsl:value-of select="date/end" />
                            </td>
                            <td>
                                <xsl:value-of select="place" />
                            </td>
                            <td>
                                <xsl:value-of select="teacher" />
                            </td>
                            <td>
                                <xsl:value-of select="faculty" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>