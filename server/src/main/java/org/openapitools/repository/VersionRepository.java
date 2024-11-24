package org.openapitools.repository;

import java.util.List;

import org.openapitools.model.Citation;
import org.openapitools.model.ProteinEntry;
import org.openapitools.model.VersionEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntry, String> {

        // ProteinEntry saveProteinEntry(ProteinEntry protein);

        // ProteinEntry updateProteinEntry(ProteinEntry protein);

        // ProteinEntry fetchProteinEntry(String pdbId);

        // void deleteProteinEntryById(String pdbId);

        // List<ProteinEntry> fetchProteinEntries();

        List<VersionEntry> findByProtein(ProteinEntry protein); 

        VersionEntry findByProteinAndMajorVersionAndMinorVersion(ProteinEntry protein, Integer majorVersion, Integer minorVersion);

}
