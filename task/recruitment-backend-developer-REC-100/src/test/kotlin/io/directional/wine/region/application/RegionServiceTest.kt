package io.directional.wine.region.application

import io.directional.wine._fixture.createRegion
import io.directional.wine._fixture.createRegionRequest
import io.directional.wine.region.application.mapper.RegionMapper
import io.directional.wine.region.application.validator.RegionValidator
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
import io.directional.wine.region.exception.NotFoundRegionException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class RegionServiceTest : BehaviorSpec({
    val regionRepository = mockk<RegionRepository>()
    val regionMapper = mockk<RegionMapper>()
    val regionValidator = mockk<RegionValidator>()
    val regionService = RegionService(
        regionRepository, regionMapper, regionValidator
    )

    afterTest {
        clearAllMocks()
    }

    Given("create") {
        val regionRequest = createRegionRequest()
        val region = createRegion()

        When("요청을 한다면") {
            every { regionMapper.dtoToEntity(regionRequest) } returns region
            every { regionRepository.save(region) } returns region

            Then("정상적으로 수행한다.") {
                regionService.create(regionRequest)

                verify(exactly = 1) { regionMapper.dtoToEntity(regionRequest) }
                verify(exactly = 1) { regionRepository.save(region) }
            }
        }
    }

    Given("update") {
        val regionRequest = createRegionRequest()
        val region = createRegion()

        When("요청을 한다면") {
            every { regionRepository.getEntityById(region.id) } returns region
            every { regionMapper.dtoToEntity(regionRequest) } returns region
            every { regionValidator.validate(region) } just runs
            every { regionRepository.save(region) } returns region

            Then("정상적으로 수행한다.") {
                regionService.update(region.id, regionRequest)

                verify(exactly = 1) { regionRepository.getEntityById(region.id) }
                verify(exactly = 1) { regionMapper.dtoToEntity(regionRequest) }
                verify(exactly = 1) { regionValidator.validate(region) }
                verify(exactly = 1) { regionRepository.save(region) }
            }
        }
    }

    Given("delete") {
        val id = 0L

        When("요청을 한다면") {
            every { regionRepository.existsById(id) } returns true
            every { regionRepository.deleteById(id) } just runs

            Then("정상적으로 수행한다.") {
                regionService.delete(id)

                verify(exactly = 1) { regionRepository.existsById(id) }
                verify(exactly = 1) { regionRepository.deleteById(id) }
            }
        }

        When("존재하지 않는 Entity라면") {
            every { regionRepository.existsById(id) } returns false
            every { regionRepository.deleteById(id) } just runs

            Then("오류가 발생한다.") {
                shouldThrow<NotFoundRegionException> {
                    regionService.delete(id)
                }

                verify(exactly = 1) { regionRepository.existsById(id) }
                verify(exactly = 0) { regionRepository.deleteById(id) }
            }
        }
    }
})
