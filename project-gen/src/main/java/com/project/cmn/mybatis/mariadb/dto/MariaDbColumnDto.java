package com.project.cmn.mybatis.mariadb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MariaDbColumnDto {
    /**
     * 테이블 이름
     */
    private String tableName;

    /**
     * 열의 이름
     */
    private String columnName;

    /**
     * 열의 NULL 가능성. 열에 NULL 값을 저장할 수 있으면 YES, 그렇지 않으면 NO입니다.
     */
    private String isNullable;

    /**
     * 컬럼의 설명
     */
    private String columnComment;

    /**
     * 열이 인덱싱되는지 여부입니다:
     * COLUMN_KEY가 비어 있으면 열이 인덱싱되지 않거나 여러 열로 구성된 고유하지 않은 인덱스에서 보조 열로만 인덱싱됩니다.
     * COLUMN_KEY가 PRI인 경우 열이 PRIMARY KEY이거나 여러 열로 구성된 PRIMARY KEY의 열 중 하나입니다.
     * COLUMN_KEY가 UNI이면 해당 칼럼은 UNIQUE 인덱스의 첫 번째 칼럼입니다.
     * (UNIQUE 인덱스는 여러 개의 NULL 값을 허용하지만 해당 열이 NULL을 허용하는지 여부는 IS_NULLABLE 열을 확인하면 알 수 있습니다.)
     * COLUMN_KEY가 MUL인 경우 해당 열은 열 내에서 주어진 값의 다중 발생이 허용되는 비고유 인덱스의 첫 번째 열입니다.
     * <p>
     * 테이블의 지정된 열에 두 개 이상의 COLUMN_KEY 값이 적용되는 경우 COLUMN_KEY는 우선순위가 가장 높은 값을 PRI, UNI, MUL 순서로 표시합니다.
     * UNIQUE 인덱스는 NULL 값을 포함할 수 없고 테이블에 PRIMARY KEY가 없는 경우 PRI로 표시될 수 있습니다.
     * 여러 열이 복합 UNIQUE 인덱스를 구성하는 경우 열의 조합은 고유하지만 각 열은 여전히 주어진 값의 여러 발생을 보유할 수 있는 경우 UNIQUE 인덱스는 MUL로 표시될 수 있습니다.
     */
    private String columnKey;

    /**
     * 열이 포함된 테이블이 속한 스키마(데이터베이스)의 이름입니다.
     */
    private String tableSchema;

    /**
     * 테이블 설명
     */
    private String tableComment;

    /**
     * 열의 기본값입니다. 열에 명시적 기본값이 NULL인 경우 또는 열 정의에 DEFAULT 절이 없는 경우 이 값은 NULL입니다.
     */
    private String columnDefault;

    /**
     * 열의 자료유형
     * DATA_TYPE 값은 다른 정보 없이 유형 이름만 포함됩니다. COLUMN_TYPE 값에는 유형 이름과 정밀도 또는 길이와 같은 기타 정보가 포함될 수 있습니다.
     */
    private String dataType;

    /**
     * 열의 자료유형
     * DATA_TYPE 값은 다른 정보 없이 유형 이름만 포함됩니다. COLUMN_TYPE 값에는 유형 이름과 정밀도 또는 길이와 같은 기타 정보가 포함될 수 있습니다.
     */
    private String columnType;

    /**
     * 문자열 열의 경우 최대 길이(문자)입니다.
     */
    private long characterMaximumLength;

    /**
     * 문자열 열의 경우 최대 길이(바이트)입니다.
     */
    private long characterOctetLength;

    /**
     * 숫자 열의 경우 숫자 정밀도입니다. ex) decimal(_precision, _scale)
     */
    private int numericPrecision;

    /**
     * 숫자 열의 경우 숫자 스케일입니다.
     */
    private int numericScale;

    /**
     * 특정 열에 대해 사용할 수 있는 추가 정보입니다. 이 경우 값은 비어 있지 않습니다:
     * <p>
     * AUTO_INCREMENT 특성이 있는 열의 경우 auto_increment.
     * ON UPDATE CURRENT_TIMESTAMP 특성이 있는 TIMESTAMP 또는 DATETIME 열의 경우 on update CURRENT_TIMESTAMP.
     * 생성된 열의 경우 STORED GENERATED 또는 VIRTUAL GENERATED.
     * 표현식 기본값이 있는 열의 경우 DEFAULT_GENERATED.
     */
    private String extra;
}