package com.younsungs.common.domain;

import java.io.Serializable;

public interface JpaId<ID extends Serializable> {
    ID getId();
}
