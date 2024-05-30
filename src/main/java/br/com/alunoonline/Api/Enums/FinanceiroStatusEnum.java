package br.com.alunoonline.Api.Enums;

import lombok.Data;

@Data
public enum FinanceiroStatusEnum {
    EM_DIA,
    EM_ATRASO,
    TRANCADO,
    CANCELADO;
}
