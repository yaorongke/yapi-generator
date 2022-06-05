package com.rkyao.yapi.generator.entity.yapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接口详细信息
 *
 * @author yaorongke
 * @date 2021/12/12
 */
@NoArgsConstructor
@Data
public class YapiInterfaceInfoDTO {

    @JsonProperty("errcode")
    private Integer errcode;
    @JsonProperty("errmsg")
    private String errmsg;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("query_path")
        private QueryPathDTO queryPath;
        @JsonProperty("edit_uid")
        private Integer editUid;
        @JsonProperty("status")
        private String status;
        @JsonProperty("type")
        private String type;
        @JsonProperty("req_body_is_json_schema")
        private Boolean reqBodyIsJsonSchema;
        @JsonProperty("res_body_is_json_schema")
        private Boolean resBodyIsJsonSchema;
        @JsonProperty("api_opened")
        private Boolean apiOpened;
        @JsonProperty("index")
        private Integer index;
        @JsonProperty("tag")
        private List<String> tag;
        @JsonProperty("_id")
        private Integer id;
        @JsonProperty("method")
        private String method;
        @JsonProperty("catid")
        private Integer catid;
        @JsonProperty("title")
        private String title;
        @JsonProperty("path")
        private String path;
        @JsonProperty("project_id")
        private Integer projectId;
        @JsonProperty("req_params")
        private List<?> reqParams;
        @JsonProperty("res_body_type")
        private String resBodyType;
        @JsonProperty("uid")
        private Integer uid;
        @JsonProperty("add_time")
        private Integer addTime;
        @JsonProperty("up_time")
        private Integer upTime;
        @JsonProperty("req_query")
        private List<ReqQueryDTO> reqQuery;
        @JsonProperty("req_headers")
        private List<?> reqHeaders;
        @JsonProperty("req_body_form")
        private List<?> reqBodyForm;
        @JsonProperty("req_body_other")
        private String reqBodyOther;
        @JsonProperty("req_body_type")
        private String reqBodyType;
        @JsonProperty("__v")
        private Integer v;
        @JsonProperty("desc")
        private String desc;
        @JsonProperty("markdown")
        private String markdown;
        @JsonProperty("res_body")
        private String resBody;
        @JsonProperty("username")
        private String username;

        @NoArgsConstructor
        @Data
        public static class QueryPathDTO {
            @JsonProperty("path")
            private String path;
            @JsonProperty("params")
            private List<?> params;
        }

        @NoArgsConstructor
        @Data
        public static class ReqQueryDTO {
            @JsonProperty("required")
            private String required;
            @JsonProperty("_id")
            private String id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("example")
            private String example;
        }
    }

}
