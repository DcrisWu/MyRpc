package com.dcris.rpc_v3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Blog implements Serializable {
    private Integer id;
    private Integer useId;
    private String title;
}
