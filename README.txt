Documentação API Para Postman

/apiCliente/inserir
{
	"nome": "",
	"cpf": "",
	"endereco": "",
	"telefone": ""
}

/apiCliente/listarTodos
/apiCliente/listarPorId/{id}
/apiCliente/buscarPorNome/{nome}
/apiCliente/buscarPorParteNome/{parte do nome}
/apiCliente/buscarPorCpf/{cpf}
/apiCliente/buscarPorTelefone/{telefone}
/apiCliente/deletar/{id}
/apiCliente/atualizar

/apiProduto/inserir
{
	"nome": "",
	"descricao": "",
	"categoria": "",
	"preco": ""
}

/apiProduto/listarTodos
/apiProduto/listarPorId/{id}
/apiProduto/buscarPorNome/{nome}
/apiProduto/buscarPorParteNome/{parte do nome}
/apiProduto/buscarPorDescricao/{descricao}
/apiProduto/buscarPorCategoria/{categoria}
/apiProduto/buscarPorPreco/{preco} //mostra todos os produtos que tiverem o valor menor ou igual que o informado
/apiProduto/deletar/{id}
/apiProduto/atualizar

/apiVenda/inserir
{
	"clienteId": {id},
	"data": "yyyy-mm-dd",
	"produtos":[
    {
      "produtoId": {id},
      "quantidade": "",
    }
}

/apiVenda/adicionarProduto/{vendaId}/{produtoId}/{quantidade}
/apiVenda/removerProduto/{vendaId}/{produtoId}
/apiVenda/editarProduto/{vendaId}/{produtoId}/{quantidade}
/apiVenda/listarTodos
/apiVenda/listarPorId/{id}
/apiVenda/listarPorCliente/{clienteid}
/apiVenda/buscarPorDataVenda/{dataVenda}
/apiVenda/buscarPorFormaDePag/{formaDePagamento}
/apiVenda/buscarPorPrecoTotal/{precoTotal}
/apiVenda/deletar/{id}
/apiVenda/atualizar
