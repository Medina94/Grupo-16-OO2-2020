package com.unla.Grupo16OO22020.services.implementation;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.entities.Producto;
import com.unla.Grupo16OO22020.models.FacturaModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.services.IPedidoService;
import com.unla.Grupo16OO22020.services.IProductoService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("jasperService")
public class JasperReportService{
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	    public String exportReport(String reportFormat, int i) throws FileNotFoundException, JRException{
	        File path = new File ("C:/documentopdf");	        
	        path.mkdirs();
	        List<FacturaModel> pedidos = pedidoService.generarFactura(i);	        
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:Factura.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedidos);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\factura.html");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\factura.pdf");
	        }

	        return "report generated in path : " + path;
	    }

	
	
}
