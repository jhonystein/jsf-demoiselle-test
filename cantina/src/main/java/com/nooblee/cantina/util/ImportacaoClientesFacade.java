package com.nooblee.cantina.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.domain.Cantina;
import com.nooblee.cantina.domain.Cliente;
import com.nooblee.cantina.domain.Empresa;

public class ImportacaoClientesFacade {

	private File arquivo;
	private ClienteBC clienteBC;
	
	public ImportacaoClientesFacade(File arquivo, ClienteBC clienteBC) {
		this.arquivo = arquivo;
		this.clienteBC = clienteBC;
	}
	
	public ImportacaoClientesFacade(String arquivo, ClienteBC clienteBC) {
		this(new File(arquivo), clienteBC);
	}
	
	public void process(Cantina cantina, Empresa empresa) throws IOException {
		InputStream is = null;
		try {
			// Criar a planilha em memoria
			is = new FileInputStream(arquivo);
			POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			// Ler apenas a primeira planilha do arquivo
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			
			// i inicia da segunda linha (primeira é cabeçalho)
			for (int i = 1; i <= rows; i++) {
	            HSSFRow row = sheet.getRow(i);
	            
	            String nome = row.getCell(0).toString();
	            String codigo = "000" + Double.valueOf("" + row.getCell(1)).intValue();
	            
	            if (clienteBC.findByCodigo(codigo).size() == 0) {
		            Cliente cli = new Cliente();
		            cli.setNome(nome);
		            cli.setCodigo(codigo);
		            cli.setCantina(cantina);
	            	cli.setEmpresa(empresa);
	            	clienteBC.insert(cli);
	            }
	        }
		} finally {
			is.close();
		}
	}
	
}
