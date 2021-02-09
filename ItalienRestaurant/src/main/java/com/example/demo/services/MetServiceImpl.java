package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.MetReponse;
import com.example.demo.dto.MetRequest;
import com.example.demo.models.Met;
import com.example.demo.repositories.MetRepository;
@Service
public class MetServiceImpl implements MetService{

	private MetRepository repos;
	private ModelMapper mapper;
	
	
	@Autowired
	public MetServiceImpl(MetRepository repos, ModelMapper mapper) {
		super();
		this.repos = repos;
		this.mapper = mapper;
	}



	@Override
	public List<MetReponse> getAllMet() {
		// TODO Auto-generated method stub
		List<Met> mets =repos.findAll();
		List<MetReponse> res= new ArrayList<>();
		for(Met met : mets)
		{
			res.add(mapper.map(met, MetReponse.class));
		}
		return res;
	}



	@Override
	public MetReponse creatMet(MetRequest request) {
		// TODO Auto-generated method stub
		Met entity=mapper.map(request, Met.class);
		Met newEntity=repos.save(entity);
		MetReponse res= new MetReponse(entity.getNom(), entity.getPrix(),null);
		return res;
	}



	@Override
	public MetReponse deleteMet(String nom) {
		// TODO Auto-generated method stub
		Optional<Met> met= repos.findById(nom);
		if (met.isPresent()) {
			MetReponse type=mapper.map(met, MetReponse.class);
			MetReponse res= new MetReponse(met.get().getNom(), met.get().getPrix(),null);
			repos.deleteById(nom);
			return res;
		}else {
			throw new NoSuchElementException("met does not existe !!");
		}
		
	}



	@Override
	public MetReponse updateMet(String nom, MetRequest request) {
		// TODO Auto-generated method stub
		Optional<Met> entity= repos.findById(nom);
		
		if(entity.isPresent()) {
			if(request.getNom()!=null)
				entity.get().setNom(request.getNom());
			else {
				entity.get().setNom(entity.get().getNom());
			}
			if(request.getPrix()!=0)
				entity.get().setPrix(request.getPrix());
			repos.save(mapper.map(entity, Met.class));
			return mapper.map(entity, MetReponse.class);
		}else {
			throw new NoSuchElementException("Met does not existe !");
		}
		
	}
	
	
	
	

}
