package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Photo;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 24.12.10
 * Time: 13:28
 */
public interface PhotoService {
    String getFormat(Photo photo);
    void saveToFile(Photo photo, String fileName) throws IOException;
    Photo loadFromFile(String fileName) throws IOException;
}
