package com.rkyao.yapi.generator.entity.yapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取某个分类下接口列表
 *
 * @author Administrator
 * @date 2022/5/29
 */
@NoArgsConstructor
@Data
public class YapiCatDTO {

    @JsonProperty("errcode")
    private Integer errcode;
    @JsonProperty("errmsg")
    private String errmsg;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("count")
        private Integer count;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("list")
        private List<ListDTO> list;

        @NoArgsConstructor
        @Data
        public static class ListDTO {
            @JsonProperty("edit_uid")
            private Integer editUid;
            @JsonProperty("status")
            private String status;
            @JsonProperty("api_opened")
            private Boolean apiOpened;
            @JsonProperty("tag")
            private List<?> tag;
            @JsonProperty("_id")
            private String id;
            @JsonProperty("method")
            private String method;
            @JsonProperty("catid")
            private String catid;
            @JsonProperty("title")
            private String title;
            @JsonProperty("path")
            private String path;
            @JsonProperty("project_id")
            private Integer projectId;
            @JsonProperty("uid")
            private Integer uid;
            @JsonProperty("add_time")
            private Integer addTime;
        }
    }

}
