package io.directional.wine

import io.directional.wine.aroma.domain.Aroma
import io.directional.wine.aroma.domain.repository.AromaRepository
import io.directional.wine.grape.domain.Grape
import io.directional.wine.grape.domain.GrapeRepository
import io.directional.wine.importer.domain.Importer
import io.directional.wine.importer.domain.repository.ImporterRepository
import io.directional.wine.pairing.domain.Pairing
import io.directional.wine.pairing.domain.repository.PairingRepository
import io.directional.wine.region.domain.Region
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.share.domain.Share
import io.directional.wine.share.domain.repository.ShareRepository
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine
import io.directional.wine.wine.domain.repository.WineRepository
import io.directional.wine.winery.domain.Winery
import io.directional.wine.winery.domain.repository.WineryRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime


@Component
class SampleDataLoader(
    private val regionRepository: RegionRepository,
    private val grapeRepository: GrapeRepository,
    private val shareRepository: ShareRepository,
    private val wineryRepository: WineryRepository,
    private val importerRepository: ImporterRepository,
    private val aromaRepository: AromaRepository,
    private val pairingRepository: PairingRepository,
    private val wineRepository: WineRepository,
) : ApplicationRunner {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    override fun run(args: ApplicationArguments) {
        println("======================================")
        println("[init sample data] start ${LocalDateTime.now()}")

        val regions = createRegion()
        val grapes = createGrape()
        createShare(grapes, regions)
        val wineries = createWinery(regions)
        createWine(regions, wineries, grapes)

        println("[init sample data] end ${LocalDateTime.now()}")
        println("======================================")
    }

    fun createRegion(): Map<String, Region> {
        val (reader, csvParser) = readerAndParser("data/region.csv")

        val regions = hashMapOf<String, Region>()
        for (data in csvParser) {
            val parentName = data.get(2)
            val parent = regions[parentName]
            val entity = Region(data.get(0), data.get(1), parent)
            regions[entity.nameKorean] = regionRepository.save(entity)
        }

        closeReaderAndParser(reader, csvParser)
        return regions
    }

    fun createGrape(): Map<String, Grape> {
        val (reader, csvParser) = readerAndParser("data/grape.csv")

        val grapes = hashMapOf<String, Grape>()
        for (data in csvParser) {
            val entity = Grape(
                data.get(0),
                data.get(1),
                data.get(2).toInt(),
                data.get(3).toInt(),
                data.get(4).toInt(),
                data.get(5).toInt()
            )
            grapes[entity.nameKorean] = grapeRepository.save(entity)
        }

        closeReaderAndParser(reader, csvParser)
        return grapes
    }

    fun createShare(grapes: Map<String, Grape>, regions: Map<String, Region>) {
        val (reader, csvParser) = readerAndParser("data/grape_share.csv")

        for (data in csvParser) {
            val grape = grapes[data.get(0)] ?: continue
            val region = regions[data.get(3)] ?: continue
            val entity = Share(
                data.get(2).toDouble(),
                grape,
                region
            )
            shareRepository.save(entity)
        }

        closeReaderAndParser(reader, csvParser)
    }

    fun createWinery(regions: Map<String, Region>): Map<String, Winery> {
        val (reader, csvParser) = readerAndParser("data/winery.csv")

        val wineries = hashMapOf<String, Winery>()
        for (data in csvParser) {
            val region = regions[data.get(2)] ?: continue
            val entity = Winery(
                data.get(0),
                data.get(1),
                region
            )
            wineryRepository.save(entity)
            wineries[entity.nameKorean] = wineryRepository.save(entity)
        }

        closeReaderAndParser(reader, csvParser)
        return wineries
    }

    fun createWine(regions: Map<String, Region>, wineries: Map<String, Winery>, grapes: Map<String, Grape>) {
        fun createWineImporter(): Map<String, Importer> {
            val (reader, csvParser) = readerAndParser("data/wine.csv")

            val importers = hashMapOf<String, Importer>()
            for (data in csvParser) {
                val wineName = data.get(1)
                val entity = Importer(data.get(13))
                importers[wineName] = entity
            }

            closeReaderAndParser(reader, csvParser)
            return importers
        }

        fun createWineAroma(): Map<String, MutableList<Aroma>> {
            val (reader, csvParser) = readerAndParser("data/wine_aroma.csv")

            val aromas = hashMapOf<String, MutableList<Aroma>>()
            for (data in csvParser) {
                val wineName = data.get(0)
                val entity = Aroma(data.get(2))
                val aromaList = aromas.getOrDefault(wineName, mutableListOf())
                aromaList.add(entity)
                aromas[wineName] = aromaList
            }

            closeReaderAndParser(reader, csvParser)
            return aromas
        }

        fun createWinePairing(): Map<String, MutableList<Pairing>> {
            val (reader, csvParser) = readerAndParser("data/wine_pairing.csv")

            val pairings = hashMapOf<String, MutableList<Pairing>>()
            for (data in csvParser) {
                val wineName = data.get(0)
                val entity = Pairing(data.get(2))
                val pairingList = pairings.getOrDefault(wineName, mutableListOf())
                pairingList.add(entity)
                pairings[wineName] = pairingList
            }

            closeReaderAndParser(reader, csvParser)
            return pairings
        }

        fun createWineGrape(grapes: Map<String, Grape>): Map<String, MutableList<Grape>> {
            val (reader, csvParser) = readerAndParser("data/wine_grape.csv")

            val grapeMap = hashMapOf<String, MutableList<Grape>>()
            for (data in csvParser) {
                val wineName = data.get(0)
                val grapeName = data.get(2)
                val entity = grapes[grapeName] ?: continue
                val grapeList = grapeMap.getOrDefault(wineName, mutableListOf())
                grapeList.add(entity)
                grapeMap[wineName] = grapeList
            }

            closeReaderAndParser(reader, csvParser)
            return grapeMap
        }

        val wineImporters = createWineImporter()
        val wineAromas = createWineAroma()
        val winePairings = createWinePairing()
        val wineGrapes = createWineGrape(grapes)

        val (reader, csvParser) = readerAndParser("data/wine.csv")

        for (data in csvParser) {
            val wineName = data.get(1)
            val winery = wineries[data.get(14)] ?: continue
            val region = regions[data.get(16)] ?: continue
            val grape = wineGrapes[wineName] ?: continue
            var importer = wineImporters[wineName] ?: continue
            importer = importerRepository.findByName(importer.name).orElseGet { importer }
            var aroma = wineAromas[wineName] ?: continue
            aroma = aroma.map { aromaRepository.findByName(it.name).orElseGet { it } }.toMutableList()
            var pairing = winePairings[wineName] ?: continue
            pairing = pairing.map { pairingRepository.findByName(it.name).orElseGet { it } }.toMutableList()
            val entity = Wine(
                Type.valueOf(data.get(0)),
                data.get(1),
                data.get(2),
                data.get(3).toDouble(),
                data.get(4).toInt(),
                data.get(5).toInt(),
                data.get(6).toInt(),
                data.get(7).toInt(),
                if (data.get(8).isEmpty()) null else data.get(8).toDouble(),
                data.get(9).toDouble(),
                data.get(10).toInt(),
                data.get(11),
                data.get(12),
                winery,
                region,
                grape,
                importer,
                aroma,
                pairing
            )
            wineRepository.save(entity)
        }

        closeReaderAndParser(reader, csvParser)
    }

    private fun readerAndParser(filePath: String): Pair<BufferedReader, CSVParser> {
        val path = Paths.get(filePath)
        val reader = Files.newBufferedReader(path)
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
        csvParser.iterator().next()

        return Pair(reader, csvParser)
    }

    private fun closeReaderAndParser(reader: BufferedReader, parser: CSVParser) {
        reader.close()
        parser.close()
    }
}