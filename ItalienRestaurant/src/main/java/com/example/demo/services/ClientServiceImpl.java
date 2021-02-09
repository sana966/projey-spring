package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.MetReponse;
import com.example.demo.models.Client;

import com.example.demo.repositories.ClientRepository;
@Service
public class ClientServiceImpl implements ClientService {
	
	private ClientRepository repos;
	private ModelMapper mapper;
	@Autowired
	public ClientServiceImpl(ClientRepository repos , ModelMapper mapper) {
		super();
		this.repos = repos;
		this.mapper=mapper;
	}


	@Override
	public List<ClientReponse> getAllClient() {
		// TODO Auto-generated method stub
		List<Client> clients =repos.findAll();
		List<ClientReponse> res= new ArrayList<>();
		for(Client client : clients)
		{
			res.add(mapper.map(client, ClientReponse.class));
		}
		return res;
	}


	@Override
	public ClientReponse createClientEntity(ClientRequest client) {
		Client entity = mapper.map(client, Client.class);
		Client newEntity = repos.save(entity);
		ClientReponse res=new ClientReponse(client.getNom(),client.getPrenom(),client.getDateNaissance(),
				client.getCourriel(),client.getTel());
		return res;
	}


	@Override
	public ClientReponse deleteClientById(long id) {
		Optional<Client> client = repos.findById(id);
		ClientReponse res=new ClientReponse(client.get().getNom(),client.get().getPrenom(),client.get().getDateNaissance(),
				client.get().getCourriel(),client.get().getTel());
		repos.deleteById(id);
		return res;
	}
	
	
	public ClientReponse getClientById(long id)
	{
		Optional<Client> opt = repos.findById(id);
		Client entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		ClientReponse client = new ClientReponse(entity.getNom(),entity.getPrenom(),entity.getDateNaissance(),entity.getCourriel(),entity.getTel());
		return client;
	}


	@Override
	public ClientReponse updateClientEntity(long id, ClientRequest request) {
		// TODO Auto-generated method stub
		Optional<Client> client = repos.findById(id);
		if(request.getNom()!=null)
			client.get().setNom(request.getNom());
		if(request.getPrenom()!=null)
			client.get().setPrenom(request.getPrenom());
		if(request.getCourriel()!=null)
			client.get().setCourriel(request.getCourriel());
		if(request.getDateNaissance()!=null)
			client.get().setDateNaissance(request.getDateNaissance());
		if(request.getTel()!=null)
			client.get().setTel(request.getTel());
		
		 repos.save(mapper.map(client, Client.class));
		return null;
		
	
	}


	@Override
	public MetReponse getMostPlatOrdred(LocalDate date) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
	
	
	
	
	

}
