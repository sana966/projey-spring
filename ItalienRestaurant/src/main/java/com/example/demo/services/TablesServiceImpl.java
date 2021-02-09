package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.TablesReponse;
import com.example.demo.dto.TablesRequest;
import com.example.demo.models.Client;
import com.example.demo.models.Tables;
import com.example.demo.repositories.TableRepository;
@Service
public class TablesServiceImpl implements TablesService {
	
	private TableRepository repos;
	private ModelMapper mapper;
	
	
	public TablesServiceImpl(TableRepository repos, ModelMapper mapper) {
		super();
		this.repos = repos;
		this.mapper = mapper;
	}


	@Override
	public List<TablesReponse> getAllTablesEntity() {
		// TODO Auto-generated method stub
		List<Tables> tables =repos.findAll();
		List<TablesReponse> res= new ArrayList<>();
		for(Tables table : tables)
		{
			res.add(mapper.map(table, TablesReponse.class));
		}
		return res;
		
	}


	@Override
	public TablesReponse createTablesEntity(TablesRequest table) {
		// TODO Auto-generated method stub
		Tables entity = mapper.map(table, Tables.class);
		Tables newEntity = repos.save(entity);
		TablesReponse res = new TablesReponse(table.getNbCouvert(),table.getType(),table.getSupplement());
		return res;
	}


	@Override
	public TablesReponse deleteTable(long numero) {
		// TODO Auto-generated method stub
		Optional<Tables> table= repos.findById(numero);
		TablesReponse res= new TablesReponse(table.get().getNbCouvert(),table.get().getType(),table.get().getSupplement());
		repos.deleteById(numero);;
		return res;
	}


	@Override
	public TablesReponse updateTable(long numero, TablesRequest request) {
		// TODO Auto-generated method stub
		Optional<Tables> test=repos.findById(numero);
		if(test.isPresent()) {
			Tables entity=mapper.map(repos.findById(numero), Tables.class);
			if(request.getNbCouvert()!=0)
				entity.setNbCouvert(request.getNbCouvert());
			if(request.getSupplement()!=0)
				entity.setSupplement(request.getSupplement());
			if(request.getType()!=null)
				entity.setType(request.getType());
			repos.save(mapper.map(entity, Tables.class));
			return mapper.map(entity, TablesReponse.class);
		}else {
			throw new NoSuchElementException("Element does not existe!");
			}
		}
	
	
	
	
	
	
	
	

}
