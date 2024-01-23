package io.directional.wine.wine.application

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine._common.utils.QueryDslUtils
import io.directional.wine._common.utils.getSingleResultFrom
import io.directional.wine.aroma.domain.QAroma
import io.directional.wine.grape.domain.QGrape
import io.directional.wine.importer.domain.QImporter
import io.directional.wine.pairing.domain.QPairing
import io.directional.wine.region.domain.QRegion
import io.directional.wine.wine.application.dto.*
import io.directional.wine.wine.domain.QWine
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.QWinery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WineQueryService(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getById(id: Long): WineDetailsResponse {
        val wine = QWine.wine
        val region = QRegion.region
        val parentRegion = QRegion("parentRegion")
        val grape = QGrape.grape
        val winery = QWinery.winery
        val wineryRegion = QRegion("wineryRegion")
        val importer = QImporter.importer
        val aroma = QAroma.aroma
        val pairing = QPairing.pairing

        return jpaQueryFactory
            .from(wine)
            .leftJoin(region).on(wine.region.id.eq(region.id))
            .leftJoin(parentRegion).on(region.id.eq(parentRegion.id))
            .leftJoin(wine.grapes, grape)
            .leftJoin(winery).on(wine.winery.id.eq(winery.id))
            .leftJoin(wineryRegion).on(winery.region.id.eq(wineryRegion.id))
            .leftJoin(importer).on(wine.importer.id.eq(importer.id))
            .leftJoin(wine.aromas, aroma)
            .leftJoin(wine.pairings, pairing)
            .where(wine.id.eq(id))
            .fetchJoin()
            .transform(
                GroupBy.groupBy(wine.id).list(
                    QWineDetailsResponse(
                        wine,
                        QWineDetailsResponse_WineryResponse(
                            winery,
                            QWineDetailsResponse_WineryResponse_RegionResponse(wineryRegion)
                        ),
                        QWineDetailsResponse_RegionResponse(region),
                        GroupBy.set(QWineDetailsResponse_GrapeResponse(grape).skipNulls()),
                        QWineDetailsResponse_ImporterResponse(importer),
                        GroupBy.set(QWineDetailsResponse_AromaResponse(aroma).skipNulls()),
                        GroupBy.set(QWineDetailsResponse_PairingResponse(pairing).skipNulls()),
                    )
                )
            )
            .getSingleResultFrom(Wine::class.java)
    }

    fun search(param: WineSearchParam, pageable: Pageable): Page<WineSummaryResponse> {
        val wine = QWine.wine
        val region = QRegion.region
        val parentRegion = QRegion("parentRegion")

        val searchFilter = searchFilter(param, wine, region)
        val query = jpaQueryFactory
            .from(wine)
            .leftJoin(wine).on(wine.region.id.eq(region.id))
            .leftJoin(parentRegion).on(region.id.eq(parentRegion.id))
            .where(searchFilter)

        val content = QueryDslUtils.fetchJoinSearchQuery(
            wine,
            QWineSummaryResponse(wine, QWineSummaryResponse_RegionResponse(region)),
            query,
            pageable
        )
        val total = QueryDslUtils.fetchTotalSearchQuery(query, region)

        return PageImpl(content, pageable, total)
    }

    private fun searchFilter(
        param: WineSearchParam,
        wine: QWine,
        region: QRegion,
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        if (param.type != null) {
            builder.and(wine.type.eq(param.type))
        }
        if (param.wineNameKorean != null) {
            builder.and(wine.nameKorean.contains(param.wineNameKorean))
        }
        if (param.wineNameEnglish != null) {
            builder.and(wine.nameEnglish.contains(param.wineNameEnglish))
        }
        if (param.minAlcohol != null) {
            builder.and(wine.alcohol.goe(param.minAlcohol))
        }
        if (param.maxAlcohol != null) {
            builder.and(wine.alcohol.loe(param.maxAlcohol))
        }
        if (param.minPrice != null) {
            builder.and(wine.price.goe(param.minPrice))
        }
        if (param.maxPrice != null) {
            builder.and(wine.price.loe(param.maxPrice))
        }
        if (param.style != null) {
            builder.and(wine.style.eq(param.style))
        }
        if (param.grade != null) {
            builder.and(wine.grade.eq(param.grade))
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