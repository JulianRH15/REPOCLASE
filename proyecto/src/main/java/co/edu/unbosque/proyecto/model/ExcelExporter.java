package co.edu.unbosque.proyecto.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import co.edu.unbosque.proyecto.dto.UsuarioDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    public byte[] generateExcel(List<UsuarioDTO> usuarios) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Usuarios");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Correo");

            // Datos
            int rowNum = 1;
            for (UsuarioDTO usuario : usuarios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(usuario.getId());
                row.createCell(1).setCellValue(usuario.getNombre());
                row.createCell(2).setCellValue(usuario.getDocumento());
                row.createCell(3).setCellValue(usuario.getFechaNacimiento());
                row.createCell(4).setCellValue(usuario.getCorreo());
                row.createCell(5).setCellValue(usuario.getClave());
            }

            // Guardar en bytes
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error al generar Excel", e);
        }
    }
}