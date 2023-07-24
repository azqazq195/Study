package com.example.jpaquerydsl.winery.application

import com.example.jpaquerydsl._common.utils.QueryDslUtils
import com.example.jpaquerydsl._common.utils.getSingleResultFrom
import com.example.jpaquerydsl.region.domain.QRegion
import com.example.jpaquerydsl.wine.domain.QWine
import com.example.jpaquerydsl.winery.application.dto.*
import com.example.jpaquerydsl.winery.domain.QWinery
import com.example.jpaquerydsl.winery.domain.Winery
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WineryQueryService(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getById(id: Long): WineryDetailsResponse {
        val winery = QWinery.winery
        val region = QRegion.region
        val wine = QWine.wine

        return jpaQueryFactory
            .from(winery)
            .leftJoin(region).on(winery.region.id.eq(region.id))
            .leftJoin(wine).on(winery.id.eq(wine.winery.id))
            .where(winery.id.eq(id))
            .fetchJoin()
            .transform(
                GroupBy.groupBy(winery.id).list(
                    QWineryDetailsResponse(
                        winery,
                        QWineryDetailsResponse_RegionResponse(region),
                        GroupBy.set(QWineryDetailsResponse_WineResponse(wine).skipNulls())
                    )
                )
            )
            .getSingleResultFrom(Winery::class.java)
    }

    fun search(param: WinerySearchParam, pageable: Pageable): Page<WinerySummaryResponse> {
        val winery = QWinery.winery
        val region = QRegion.region

        val searchFilter = searchFilter(param, winery, region)
        val query = jpaQueryFactory
            .from(winery)
            .leftJoin(region).on(winery.region.id.eq(region.id))
            .where(searchFilter)

        val content = QueryDslUtils.fetchJoinSearchQuery(
            winery,
            QWinerySummaryResponse(winery, QWinerySummaryResponse_RegionResponse(region)),
            query,
            pageable,
        )
        val total = QueryDslUtils.fetchTotalSearchQuery(query, winery)

        return PageImpl(content, pageable, total)
    }

    private fun searchFilter(
        param: WinerySearchParam,
        winery: QWinery,
        region: QRegion,
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        if (param.wineryNameKorean != null) {
            builder.and(winery.nameKorean.contains(param.wineryNameKorean))
        }

        if (param.wineryNameEnglish != null) {
            builder.and(winery.nameEnglish.contains(param.wineryNameEnglish))
        }

        if (param.regionNameKorean != null) {
            builder.and(region.nameKorean.contains(param.regionNameKorean))
        }

        if (param.regionNameEnglish != null) {
            builder.and(region.nameEnglish.contains(param.regionNameEnglish))
        }

        return builder
    }
}