package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.MetReponse;
import com.example.demo.models.Client;

public interface ClientService {

	List<ClientReponse> getAllClient();
	ClientReponse createClientEntity(ClientRequest request);
	ClientReponse deleteClientById(long id);
	ClientReponse updateClientEntity(long id,ClientRequest request);
	MetReponse getMostPlatOrdred(LocalDate date);
}
