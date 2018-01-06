package com.sp.wls.common;

import java.io.Serializable;
import java.util.Objects;

public class LocatedHyperlinkModel implements Serializable{

    private String linkUrl;
    private String cssSelector;

    public LocatedHyperlinkModel(String linkUrl, String cssSelector) {
        this.linkUrl = linkUrl;
        this.cssSelector = cssSelector;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocatedHyperlinkModel that = (LocatedHyperlinkModel) o;
        return Objects.equals(linkUrl, that.linkUrl) &&
                Objects.equals(cssSelector, that.cssSelector);
    }

    @Override
    public int hashCode() {

        return Objects.hash(linkUrl, cssSelector);
    }
}
