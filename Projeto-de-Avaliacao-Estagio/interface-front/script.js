// Configurações da API
const API_BASE_URL = "http://localhost:8080/api";

// Alternar visibilidade de seções
function toggleSections(visibleSection, hiddenSection) {
    document.getElementById(visibleSection).style.display = 'block';
    document.getElementById(hiddenSection).style.display = 'none';
}

// Eventos para alternar seções
function setupToggleEvents() {
    document.getElementById('addCliente').addEventListener('click', () => toggleSections('cadastroClientes', 'consultaClientes'));
    document.getElementById('cancelCliente').addEventListener('click', () => toggleSections('consultaClientes', 'cadastroClientes'));
    document.getElementById('addProduto').addEventListener('click', () => toggleSections('cadastroProdutos', 'consultaProdutos'));
    document.getElementById('cancelProduto').addEventListener('click', () => toggleSections('consultaProdutos', 'cadastroProdutos'));
    document.getElementById('addVenda').addEventListener('click', () => toggleSections('cadastroVendas', 'consultaVendas'));
    document.getElementById('cancelVenda').addEventListener('click', () => toggleSections('consultaVendas', 'cadastroVendas'));
}

// Carregar clientes e produtos para as comboboxes
async function loadComboboxes() {
    const clientes = await fetchData(`${API_BASE_URL}/clientes`);
    const produtos = await fetchData(`${API_BASE_URL}/produtos`);

    const clienteSelect = document.getElementById('vendaCliente');
    clientes.forEach(cliente => {
        const option = document.createElement('option');
        option.value = cliente.id;
        option.textContent = cliente.nome;
        clienteSelect.appendChild(option);
    });

    updateProdutoCombobox(produtos);
}

function updateProdutoCombobox(produtos) {
    const vendaProdutos = document.getElementById('vendaProdutos');
    vendaProdutos.innerHTML = '';
    produtos.forEach(produto => {
        const productDiv = document.createElement('div');
        const select = document.createElement('select');
        const quantidade = document.createElement('input');

        select.classList.add('produtoCombo');
        produtos.forEach(produtoOption => {
            const option = document.createElement('option');
            option.value = produtoOption.id;
            option.textContent = produtoOption.nome;
            option.setAttribute('data-preco', produtoOption.preco);
            select.appendChild(option);
        });

        quantidade.type = 'number';
        quantidade.min = 1;
        quantidade.value = 1;
        quantidade.classList.add('produtoQuantidade');

        productDiv.appendChild(select);
        productDiv.appendChild(quantidade);
        vendaProdutos.appendChild(productDiv);
    });
}

// Adicionar produto no formulário de vendas
function setupAddProductButton() {
    document.getElementById('addProdutoVenda').addEventListener('click', async () => {
        const produtos = await fetchData(`${API_BASE_URL}/produtos`);
        updateProdutoCombobox(produtos);
    });
}

// Calcular valor total da venda
function calcularValorTotal() {
    const produtoCombos = document.querySelectorAll('.produtoCombo');
    const produtoQuantidades = document.querySelectorAll('.produtoQuantidade');
    const vendaTotal = document.getElementById('vendaTotal');

    let total = 0;
    produtoCombos.forEach((combo, index) => {
        const produtoId = combo.value;
        const quantidade = parseInt(produtoQuantidades[index].value);
        const produtoPreco = parseFloat(combo.options[combo.selectedIndex].getAttribute('data-preco'));
        total += produtoPreco * quantidade;
    });

    vendaTotal.value = total.toFixed(2);
}

// Função para carregar dados da API
async function fetchData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        console.error(`Erro ao buscar dados: ${response.statusText}`);
        return [];
    }
    return response.json();
}

// Enviar dados para a API
async function salvarVenda(event) {
    event.preventDefault();

    const clienteId = document.getElementById('vendaCliente').value;
    const produtos = Array.from(document.querySelectorAll('.produtoCombo')).map((combo, index) => ({
        id: combo.value,
        quantidade: parseInt(document.querySelectorAll('.produtoQuantidade')[index].value)
    }));
    const formaPagamento = document.getElementById('vendaPagamento').value;
    const valorTotal = document.getElementById('vendaTotal').value;

    const venda = {
        clienteId,
        produtos,
        formaPagamento,
        valorTotal
    };

    const response = await fetch(`${API_BASE_URL}/vendas`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(venda)
    });

    if (response.ok) {
        console.log('Venda salva com sucesso');
        toggleSections('consultaVendas', 'cadastroVendas');
    } else {
        console.error(`Erro ao salvar venda: ${response.statusText}`);
    }
}

// Configuração inicial
function init() {
    setupToggleEvents();
    setupAddProductButton();
    loadComboboxes();
    document.getElementById('formVendas').addEventListener('submit', salvarVenda);

    // Atualiza o valor total quando produtos ou quantidades mudam
    document.getElementById('vendaProdutos').addEventListener('input', calcularValorTotal);
}

// Inicializar o script quando a página carregar
window.addEventListener('DOMContentLoaded', init);
