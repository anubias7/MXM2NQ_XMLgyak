<?xml version="1.0" encoding="utf-8" standalone="no"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"></xsl:output>

    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <h2>Kormos Máté apple-template</h2>
                <xsl:apply-templates />
            </body>
        </html>

    </xsl:template>
    <xsl:template match="student">
        <p>
            <xsl:apply-templates select="@id" />
            <xsl:apply-templates select="vezeteknev" />
            <xsl:apply-templates select="keresztnev" />
            <xsl:apply-templates select="becenev" />
            <xsl:apply-templates select="kor" />
            <xsl:apply-templates select="fizetes" />


        </p>
    </xsl:template>
    <xsl:template match="@id">
        ID:
        <span>
            <xsl:value-of select="." />
        </span>
        <br />
    </xsl:template>

    <xsl:template match="vezeteknev">
        Vezeteknev:
        <span style="color:green;">
            <xsl:value-of select="." />
        </span>
        <br />
    </xsl:template>

    <xsl:template match="keresztnev">
        Keresztnev:
        <span style="color:brown;">
            <xsl:value-of select="." />
        </span>
        <br />
    </xsl:template>

    <xsl:template match="becenev">
        <span>
            <xsl:value-of select="."></xsl:value-of>
        </span>
    </xsl:template>

    <xsl:template match="kor">
        <span>
            Kor:
            <span style="color:blue;">
                <xsl:value-of select="."></xsl:value-of>
            </span>
        </span>
        <br />
    </xsl:template>
    <xsl:template match="fizetes">
        Fizetés:
        <span style="color: red;">
            <xsl:value-of select="."></xsl:value-of>
        </span>
    </xsl:template>
</xsl:stylesheet>