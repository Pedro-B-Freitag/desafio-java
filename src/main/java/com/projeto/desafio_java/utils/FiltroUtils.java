package com.projeto.desafio_java.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilder;

import java.math.BigDecimal;
import java.util.Map;

public final class FiltroUtils {

    public static BooleanBuilder montarFiltros(Map<String, Object> filtros, PathBuilder<?> entityPath) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(entityPath.getBoolean("ativo").isTrue());
        if (filtros == null) return builder;

        filtros.forEach((campo, valor) -> {

            if (valor == null) return;

            Class<?> tipo = entityPath.get(campo).getType();

            if (tipo.equals(String.class)) {
                builder.and(entityPath.getString(campo).containsIgnoreCase(valor.toString()));
            }
            else if (Number.class.isAssignableFrom(tipo)) {
                gerarFiltroNumero(builder, campo, valor, tipo, entityPath);
            }
            else if (tipo.equals(Boolean.class)) {
                builder.and(entityPath.getBoolean(campo).eq((Boolean) valor));
            }
            else if (tipo.isEnum()) {
                gerarFiltroEnum(builder, campo, valor, tipo, entityPath);
            }
            else {
                builder.and(entityPath.get(campo).eq(valor));
            }
        });

        return builder;
    }

    private static void gerarFiltroEnum(BooleanBuilder builder, String campo, Object valor, Class<?> tipo, PathBuilder<?> entityPath) {
        Enum<?> enumValue;
        if (valor instanceof String) {
            enumValue = Enum.valueOf((Class<Enum>) tipo, valor.toString());
        } else {
            enumValue = (Enum<?>) valor;
        }
        builder.and(entityPath.get(campo).eq(enumValue));
    }

    private static void gerarFiltroNumero(BooleanBuilder builder, String campo, Object valor, Class<?> tipo, PathBuilder<?> entityPath) {
        Number number = converterNumber(valor, tipo);
        if(number != null){
            builder.and(entityPath.get(campo).eq(number));
        }
    }

    private static Number converterNumber(Object valor, Class<?> tipoNumero) {
        String stringValue = valor.toString();

        if (Integer.class.equals(tipoNumero) || int.class.equals(tipoNumero)) {
            return Integer.valueOf(stringValue);
        } else if (Long.class.equals(tipoNumero) || long.class.equals(tipoNumero)) {
            return Long.valueOf(stringValue);
        } else if (Double.class.equals(tipoNumero) || double.class.equals(tipoNumero)) {
            return Double.valueOf(stringValue);
        } else if (Float.class.equals(tipoNumero) || float.class.equals(tipoNumero)) {
            return Float.valueOf(stringValue);
        } else if (BigDecimal.class.equals(tipoNumero)) {
            return new BigDecimal(stringValue);
        }
        return null;
    }

}
