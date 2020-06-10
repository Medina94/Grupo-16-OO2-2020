package com.unla.Grupo16OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.converters.ComisionConverter;
import com.unla.Grupo16OO22020.converters.PedidoConverter;
import com.unla.Grupo16OO22020.entities.Comision;
import com.unla.Grupo16OO22020.models.ComisionModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.repositories.IComisionRepository;
import com.unla.Grupo16OO22020.repositories.IPedidoRepository;
import com.unla.Grupo16OO22020.services.IComisionService;

@Service("comisionService")
public class ComisionService implements IComisionService{

	@Autowired
	@Qualifier("comisionRepository")
	private IComisionRepository comisionRepository;
	
	@Autowired
	@Qualifier("comisionConverter")
	private ComisionConverter comisionConverter;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	
	@Override
	public ComisionModel insertOrUpdate(ComisionModel comisionModel) {
		Comision comision = comisionRepository.save(comisionConverter.modelToEntity(comisionModel));
		return comisionConverter.entityToModel(comision);
	}
	
	@Override
	public ComisionModel findById(int id) {
		return comisionConverter.entityToModel(comisionRepository.findById(id));
	}
	
	@Override
	public List<Comision> getAll(){
		return comisionRepository.getAll(); //cambio de findAll a getAll
	}
	
//	@Override
//	public void calculoDeComision(int año,int mes,EmpleadoModel empleadoModel) {
//		List<PedidoModel> pedido=pedidoRepository.findBySolicitador_id(empleadoModel.getId());
//		int precio=0;
//		double comisionPorcentaje,comisionTotal=0;
//		ComisionModel comision=comisionRepository.traerMasReciente();
//		
//		for (PedidoModel pedidoModel : pedido) {
//			if(pedidoModel.getFecha().getYear()==año && pedidoModel.getFecha().getMonthValue()==mes) {
//			precio=precio+(pedidoModel.getProductoModel().getPrecioUnitario()*pedidoModel.getCantidadSolicitada());
//			comisionPorcentaje=comision.getPlusPedido()/100;
//			comisionTotal=comisionTotal+(comisionPorcentaje*precio);
//			}
//		}
//		empleadoModel.setSueldo((int)comisionTotal);
//		
//	}
	
//	@Override
//	public ComisionModel traerMasReciente() {
//		return comisionRepository.traerMasReciente();
//	}
	
}
