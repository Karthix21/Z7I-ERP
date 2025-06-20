package com.example.z7I.dto;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class PagedResponse<T> extends RepresentationModel<PagedResponse<T>> {
    private final List<T> content;
    private final PagedModel.PageMetadata pageMetadata;

    public PagedResponse(Page<T> page) {
        this.content = page.getContent();
        this.pageMetadata = new PagedModel.PageMetadata(
            page.getSize(),
            page.getNumber(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

    public PagedModel.PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    public List<T> getContent() {
        return content;
    }
}
