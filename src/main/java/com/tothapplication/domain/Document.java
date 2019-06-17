package com.tothapplication.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.tothapplication.domain.enumeration.TypeDocument;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "mime_type")
    private String mimeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_doc")
    private TypeDocument typeDoc;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "document_ccp",
               joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "ccp_id", referencedColumnName = "id"))
    private Set<CCP> cCPS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Document title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Document mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public TypeDocument getTypeDoc() {
        return typeDoc;
    }

    public Document typeDoc(TypeDocument typeDoc) {
        this.typeDoc = typeDoc;
        return this;
    }

    public void setTypeDoc(TypeDocument typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Set<CCP> getCCPS() {
        return cCPS;
    }

    public Document cCPS(Set<CCP> cCPS) {
        this.cCPS = cCPS;
        return this;
    }

    public Document addCCP(CCP cCP) {
        this.cCPS.add(cCP);
        cCP.getDocuments().add(this);
        return this;
    }

    public Document removeCCP(CCP cCP) {
        this.cCPS.remove(cCP);
        cCP.getDocuments().remove(this);
        return this;
    }

    public void setCCPS(Set<CCP> cCPS) {
        this.cCPS = cCPS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", mimeType='" + getMimeType() + "'" +
            ", typeDoc='" + getTypeDoc() + "'" +
            "}";
    }
}
