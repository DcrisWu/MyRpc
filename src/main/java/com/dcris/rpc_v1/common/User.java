package com.dcris.rpc_v1.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    private Integer id;
    private String userName;
    private boolean sex;
}
