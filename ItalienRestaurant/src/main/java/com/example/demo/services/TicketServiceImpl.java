package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TicketReponse;
import com.example.demo.dto.TicketRequest;
import com.example.demo.models.Ticket;
import com.example.demo.repositories.TicketRepository;
@Service
public class TicketServiceImpl implements TicketService {
	
	TicketRepository repos ;
	ModelMapper mapper;
	
	
	@Autowired
	public TicketServiceImpl(TicketRepository repos, ModelMapper mapper) {
		super();
		this.repos = repos;
		this.mapper = mapper;
	}



	@Override
	public List<TicketReponse> getAllTicket() {
		// TODO Auto-generated method stub
		List<Ticket> tickets= repos.findAll();
		List<TicketReponse> res=new ArrayList<>();
		for(Ticket ticket:tickets)
			res.add(mapper.map(ticket, TicketReponse.class));
		return res;
	}



	@Override
	public TicketReponse createTicketEntity(TicketRequest request) {
		// TODO Auto-generated method stub
		Ticket entity=mapper.map(request, Ticket.class);
		repos.save(entity);
		TicketReponse res= new TicketReponse(request.getDate(), request.getAddition(),request.getNbCouvert(), request.getClient(), request.getTables());
		return res;
	}



	@Override
	public TicketReponse deleteTicket(long numero) {
		// TODO Auto-generated method stub
		Optional<Ticket> ticket= repos.findById(numero);
		if(ticket.isPresent()) {
			//ticket.get().getDate()
		TicketReponse res=new TicketReponse(ticket.get().getDate(), ticket.get().getAddition(),ticket.get().getNbCouvert(),ticket.get().getClient(),ticket.get().getTables());
		repos.deleteById(numero);
		return res;}
		else {
			throw new NoSuchElementException("ticket does not existe !");
		}
	}



	@Override
	public TicketReponse updateTicket(long numero, TicketRequest request) {
		// TODO Auto-generated method stub
		Optional<Ticket> entity = repos.findById(numero);
		if(entity.isPresent()) {
			if(request.getDate()!=null)
				entity.get().setDate(request.getDate());
			if(request.getNbCouvert()!=0)
				entity.get().setNbCouvert(request.getNbCouvert());
			if(request.getAddition()!=0)
				entity.get().setAddition(request.getAddition());
			if(request.getClient()!=null)
				entity.get().setClient(request.getClient());
			if(request.getTables()!=null)
				entity.get().setTables(request.getTables());
			repos.save(mapper.map(entity, Ticket.class));
			return mapper.map(entity, TicketReponse.class);
			//TicketReponse res = new TicketReponse(entity.get().getDate(), entity.get().getAddition(),entity.get().getNbCouvert(),entity.get().getClient(),entity.get().getTables());
			//return res;
		}else {
			throw new NoSuchElementException("ticket does not existe !");
		}
		
	}
	
	

}
