package com.example.tk_etlproc.reading.sources.ftp;

import com.example.tk_etlproc.api.DTO.source.ConfigFTPDTO;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.reading.sources.Source;
import org.apache.commons.net.ftp.FTPClient;

import java.util.List;

public class FTPSource extends Source {
    public List<OutputFromStep> read(ConfigFTPDTO configFTPDTO){
        FTPClient ftp = new FTPClient();
return null;
    }
}
