package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.archive.manager.ArchiveManager;
import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.archive.save.ArchiveSaveType;
import com.example.tk_etlproc.reading.sources.Source;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public abstract class FileSource extends Source {


   private ArchiveManager manager;

   public FileSource(ArchiveManager manager) {
      this.manager = manager;
   }

   List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {

      if(configFileDTO.isArchivePayload()) manager.saveArchive( configFileDTO, ArchiveSaveType.IN);
      return null;
   }


}
