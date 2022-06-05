package com.rkyao.yapi.generator.entity.yapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * class
 *
 * @author yaorongke
 * @date 2022/5/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YapiPropertiesDTO {

    private String type;

    private String description;

    private Map<String, YapiPropertiesDTO> properties;

    private YapiPropertiesDTO items;

}
