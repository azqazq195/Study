package com.example.jpaquerydsl.region.application

import com.example.jpaquerydsl._fixture.*
import com.example.jpaquerydsl.grape.domain.GrapeRepository
import com.example.jpaquerydsl.region.domain.repository.RegionRepository
import com.example.jpaquerydsl.share.domain.repository.ShareRepository
import com.example.jpaquerydsl.wine.domain.repository.WineRepository
import com.example.jpaquerydsl.winery.domain.repository.WineryRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class RegionQueryServiceTest @Autowired constructor(
    private val regionQueryService: RegionQueryService,
    private val regionRepository: RegionRepository,
    private val grapeRepository: GrapeRepository,
    private val shareRepository: ShareRepository,
    private val wineryRepository: WineryRepository,
    private val wineRepository: WineRepository,
) : BehaviorSpec({
    extension(SpringTestExtension(SpringTestLifecycleMode.Root))

    Given("getById") {
        val region = createRegion()
        val grape1 = createGrape(nameKorean = "포도")
        val grape2 = createGrape(nameKorean = "거봉")
        val share1 = createShare(grape = grape1, region = region)
        val share2 = createShare(grape = grape2, region = region)
        val winery = createWinery(region = region)
        val wine = createWine(winery = winery, region = region, grapes = mutableListOf(grape1, grape2))

        regionRepository.save(region)
        grapeRepository.save(grape1)
        grapeRepository.save(grape2)
        shareRepository.save(share1)
        shareRepository.save(share2)
        wineryRepository.save(winery)
        wineRepository.save(wine)

        When("요청을 한다면") {
            val result = regionQueryService.getById(region.id)

            Then("반환") {
                result shouldBe createRegionDetailsResponse(
                    region, setOf(grape1, grape2), setOf(winery), setOf(wine)
                )
            }
        }
    }

    Given("getById - relation 데이터가 없는 경우") {
        val region1 = createRegion()
        val region2 = createRegion(parent = region1)

        regionRepository.save(region1)
        regionRepository.save(region2)

        When("요청을 한다면") {
            val result = regionQueryService.getById(region2.id)

            Then("반환") {
                result shouldBe createRegionDetailsResponse(
                    region2, setOf(), setOf(), setOf()
                )
            }
        }
    }

    Given("search - 5개의 region 제공") {
        val region1 = createRegion(nameKorean = "13", nameEnglish = "5")
        val region2 = createRegion(nameKorean = "23", nameEnglish = "4", parent = region1)
        val region3 = createRegion(nameKorean = "33", nameEnglish = "3", parent = region1)
        val region4 = createRegion(nameKorean = "43", nameEnglish = "2", parent = region2)
        val region5 = createRegion(nameKorean = "53", nameEnglish = "1", parent = region3)

        val regions = listOf(region1, region2, region3, region4, region5)
        regionRepository.saveAll(regions)

        When("5개의 region의 이름이 포함된 이름을 검색한다면") {
            val search = createRegionSearchParam(nameKorean = "3")
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("5개 반환") {
                result.totalElements shouldBe 5
                result.totalPages shouldBe 1
                result.content shouldBe regions.map(::createRegionSummaryResponse)
            }
        }

        When("검색내용 없이 검색한다면") {
            val search = createRegionSearchParam()
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("5개 반환") {
                result.totalElements shouldBe 5
                result.totalPages shouldBe 1
                result.content shouldBe regions.map(::createRegionSummaryResponse)
            }
        }

        When("0개의 region의 이름이 포함된 이름을 검색한다면") {
            val search = createRegionSearchParam(nameKorean = "없는 이름")
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("0개 반환") {
                result.totalElements shouldBe 0
                result.totalPages shouldBe 0
                result.content shouldBe listOf()
            }
        }

        When("5개의 region의 이름이 포함된 이름을 검색하고 PageSize가 3인 경우라면") {
            val search = createRegionSearchParam(nameKorean = "3")
            val pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("3개 반환") {
                result.totalElements shouldBe 5
                result.totalPages shouldBe 2
                result.content shouldBe regions.subList(0, 3).map(::createRegionSummaryResponse)
            }
        }

        When("1개의 region의 이름이 포함된 이름을 검색한다면") {
            val search = createRegionSearchParam(nameKorean = region3.nameKorean)
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("5개 반환") {
                result.totalElements shouldBe 1
                result.totalPages shouldBe 1
                result.content shouldBe regions.filter { it == region3 }.map(::createRegionSummaryResponse)
            }
        }

        When("2개의 region의 상위 region의 이름이 포함된 이름을 검색한다면") {
            val search = createRegionSearchParam(parentNameKorean = region1.nameKorean)
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("2개 반환") {
                result.totalElements shouldBe 2
                result.totalPages shouldBe 1
                result.content shouldBe regions.filter { it.parent == region1 }.map(::createRegionSummaryResponse)
            }
        }

        When("잘못된 property로 정렬한다면") {
            val search = createRegionSearchParam()
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "wrong"))

            Then("5개 반환") {
                shouldThrow<IllegalArgumentException> {
                    regionQueryService.search(search, pageable)
                }
            }
        }
    }

    Given("search - region 제공 X") {
        When("검색한다면") {
            val search = createRegionSearchParam()
            val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nameKorean"))

            val result = regionQueryService.search(search, pageable)

            Then("0개 반환") {
                result.totalElements shouldBe 0
                result.totalPages shouldBe 0
                result.content shouldBe listOf()
            }
        }
    }
})