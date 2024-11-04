package org.openapitools.model;

import java.io.Serializable;

public class  VersionId implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer majorVersion;

    public Integer minorVersion;

    public VersionId(Integer majorVersion, Integer minorVersion){
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion; 
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((majorVersion == null) ? 0 : majorVersion.hashCode());
        result = prime * result + ((minorVersion == null) ? 0 : minorVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VersionId other = (VersionId) obj;
        if (this.majorVersion == null) {
            if (other.majorVersion != null)
                return false;
        } else if (!this.majorVersion.equals(other.majorVersion))
            return false;
        if (this.minorVersion == null) {
            if (other.minorVersion != null)
                return false;
        } else if (!this.minorVersion.equals(other.minorVersion))
            return false;
        return true;
    }
}
