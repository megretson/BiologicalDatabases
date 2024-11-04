package org.openapitools.repository;

import java.util.List;

import org.openapitools.model.Citation;
import org.openapitools.model.ProteinEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends JpaRepository<ProteinEntry, String> {

        // ProteinEntry saveProteinEntry(ProteinEntry protein);

        // ProteinEntry updateProteinEntry(ProteinEntry protein);

        // ProteinEntry fetchProteinEntry(String pdbId);

        // void deleteProteinEntryById(String pdbId);

        // List<ProteinEntry> fetchProteinEntries();

}
