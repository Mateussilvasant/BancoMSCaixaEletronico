package br.com.bancoms.service;

import br.com.bancoms.dao.ClienteDAO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.vo.ClienteVO;

public class ClienteService
{

    private static ClienteService clienteService;
    private ClienteDAO clienteDAO;

    public ClienteService()
    {
	clienteDAO = new ClienteDAO();
    }

    public static ClienteService getInstance()
    {

	if (clienteService != null)
	{
	    return clienteService;
	}

	clienteService = new ClienteService();

	return clienteService;

    }

    public Cliente realizarLogin(ClienteVO clienteVO)
    {
	return clienteDAO.realizarLogin(clienteVO);
    }

}
