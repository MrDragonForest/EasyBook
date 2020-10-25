package com.dragonforest.easybook.database.mapper

/**
 *
 * create by DragonForest at 2020/7/12
 */
open abstract class BaseMapper<D, E> {
    abstract fun mapToEntity(data: D): E
    abstract fun mapToData(entity: E): D
}