const API_URL = 'http://localhost:8080/';
const API_URL_VENDA = 'http://localhost:8080/apiVenda';
const API_CLIENTE = 'http://localhost:8080/apiCliente';
const API_PRODUTO = 'http://localhost:8080/apiProduto';

// Estado da aplicação
let editingId = null;
let clientes = [];
let produtos = [];

// Elementos do DOM
const produtoForm = document.getElementById('produtoForm');
const vendaForm = document.getElementById('vendaForm');
const clienteForm = document.getElementById('clienteForm');
const formTitle = document.getElementById('formTitle');
const submitText = document.getElementById('submitText');
const cancelEdit = document.getElementById('cancelEdit');
const searchType = document.getElementById('searchType');
const searchTerm = document.getElementById('searchTerm');
const searchButton = document.getElementById('searchButton');
const produtosTable = document.getElementById('produtosTable');
const vendasTable = document.getElementById('vendasTable');
const clientesTable = document.getElementById('clientesTable');
const adicionarProdutoBtn = document.getElementById('adicionarProduto');
const produtosVendaDiv = document.getElementById('produtosVenda');

// Funções auxiliares
const formatResponse = async (response) => {
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
};

const showError = (error) => {
  console.error('Erro:', error);
  alert('Ocorreu um erro. Por favor, tente novamente.');
};

const formatMoney = (value) => {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value);
};

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString('pt-BR');
};

// Funções de CRUD para Produtos
async function fetchProdutos() {
  try {
    const response = await fetch(`${API_URL}/listarTodos`);
    const produtos = await formatResponse(response);
    renderProdutos(produtos);
  } catch (error) {
    showError(error);
  }
}

async function saveProduto(produto) {
  try {
    const url = editingId ? `${API_URL}/atualizar` : `${API_URL}/inserir`;
    const method = editingId ? 'PUT' : 'POST';
    
    const response = await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(produto),
    });
    
    await formatResponse(response);
    resetForm();
    fetchProdutos();
  } catch (error) {
    showError(error);
  }
}

async function deleteProduto(id) {
  if (!confirm('Tem certeza que deseja excluir este produto?')) return;
  
  try {
    const response = await fetch(`${API_URL}/deletar/${id}`, {
      method: 'DELETE',
    });
    await formatResponse(response);
    fetchProdutos();
  } catch (error) {
    showError(error);
  }
}

// Funções de UI para Produtos
function renderProdutos(produtos) {
  produtosTable.innerHTML = produtos.map(produto => `
    <tr>
      <td>${produto.nome}</td>
      <td>${produto.descricao}</td>
      <td>${produto.categoria}</td>
      <td>${formatMoney(produto.preco)}</td>
      <td>
        <div class="action-buttons">
          <button onclick="handleEditProduto(${JSON.stringify(produto)})" class="btn-icon btn-edit">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
              <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
            </svg>
          </button>
          <button onclick="deleteProduto(${produto.id})" class="btn-icon btn-delete">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="3 6 5 6 21 6"></polyline>
              <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
              <line x1="10" y1="11" x2="10" y2="17"></line>
              <line x1="14" y1="11" x2="14" y2="17"></line>
            </svg>
          </button>
        </div>
      </td>
    </tr>
  `).join('');
}

function handleEditProduto(produto) {
  editingId = produto.id;
  document.getElementById('produtoId').value = produto.id;
  document.getElementById('nome').value = produto.nome;
  document.getElementById('descricao').value = produto.descricao;
  document.getElementById('categoria').value = produto.categoria;
  document.getElementById('preco').value = produto.preco;
  
  formTitle.textContent = 'Editar Produto';
  submitText.textContent = 'Atualizar';
  cancelEdit.style.display = 'inline-flex';
}

function resetForm() {
  editingId = null;
  produtoForm.reset();
  formTitle.textContent = 'Novo Produto';
  submitText.textContent = 'Salvar';
  cancelEdit.style.display = 'none';
}

// Event Listeners para Produtos
produtoForm.addEventListener('submit', (e) => {
  e.preventDefault();
  
  const produto = {
    id: editingId,
    nome: document.getElementById('nome').value,
    descricao: document.getElementById('descricao').value,
    categoria: document.getElementById('categoria').value,
    preco: parseFloat(document.getElementById('preco').value),
  };
  
  saveProduto(produto);
});

cancelEdit.addEventListener('click', resetForm);

// Funções de CRUD para Vendas
async function fetchVendas() {
  try {
    const response = await fetch(`${API_URL_VENDA}/listarTodos`);
    const vendas = await formatResponse(response);
    renderVendas(vendas);
  } catch (error) {
    showError(error);
  }
}

async function saveVenda(venda) {
  try {
    const url = editingId ? `${API_URL_VENDA}/atualizar/${editingId}` : `${API_URL_VENDA}/inserir`;
    const method = editingId ? 'PUT' : 'POST';
    
    const response = await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(venda),
    });
    
    await formatResponse(response);
    resetForm();
    fetchVendas();
  } catch (error) {
    showError(error);
  }
}

async function deleteVenda(id) {
  if (!confirm('Tem certeza que deseja excluir esta venda?')) return;
  
  try {
    const response = await fetch(`${API_URL_VENDA}/deletar/${id}`, {
      method: 'DELETE',
    });
    await formatResponse(response);
    fetchVendas();
  } catch (error) {
    showError(error);
  }
}

// Funções de UI para Vendas
function renderVendas(vendas) {
  vendasTable.innerHTML = vendas.map(venda => {
    const cliente = clientes.find(c => c.id === venda.cliente.id);
    return `
      <tr>
        <td>${cliente ? cliente.nome : 'Cliente não encontrado'}</td>
        <td>${formatDate(venda.dataVenda)}</td>
        <td>${venda.formaDePag}</td>
        <td>${formatMoney(venda.precoTotal)}</td>
        <td>
          <div class="action-buttons">
            <button onclick="handleEditVenda(${JSON.stringify(venda)})" class="btn-icon btn-edit">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
              </svg>
            </button>
            <button onclick="deleteVenda(${venda.id})" class="btn-icon btn-delete">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <polyline points="3 6 5 6 21 6"></polyline>
                <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                <line x1="10" y1="11" x2="10" y2="17"></line>
                <line x1="14" y1="11" x2="14" y2="17"></line>
              </svg>
            </button>
          </div>
        </td>
      </tr>
    `;
  }).join('');
}

function handleEditVenda(venda) {
  editingId = venda.id;
  document.getElementById('vendaId').value = venda.id;
  document.getElementById('dataVenda').value = venda.dataVenda;
  document.getElementById('formDePagamento').value = venda.formaDePag;
  document.getElementById('precoTotal').value = venda.precoTotal;
  formTitle.textContent = 'Editar Venda';
  submitText.textContent = 'Atualizar';
  cancelEdit.style.display = 'inline-flex';
}

function resetVendaForm() {
  editingId = null;
  vendaForm.reset();
  formTitle.textContent = 'Nova Venda';
  submitText.textContent = 'Salvar';
  cancelEdit.style.display = 'none';
}

// Event Listener para Vendas
vendaForm.addEventListener('submit', (e) => {
  e.preventDefault();
  
  const venda = {
    id: editingId,
    dataVenda: document.getElementById('dataVenda').value,
    formaDePag: document.getElementById('formDePagamento').value,
    precoTotal: parseFloat(document.getElementById('precoTotal').value),
    cliente: {
      id: parseInt(document.getElementById('clienteVenda').value),
    },
  };
  
  saveVenda(venda);
});

cancelEdit.addEventListener('click', resetVendaForm);