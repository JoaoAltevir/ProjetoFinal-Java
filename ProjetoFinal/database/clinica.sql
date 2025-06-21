-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 20/06/2025 às 17:05
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `clinica`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `agendamento`
--

CREATE TABLE `agendamento` (
  `id_agendamento_exame` int(11) NOT NULL,
  `data_hora` datetime NOT NULL,
  `valor_pago` decimal(10,2) NOT NULL,
  `realizado` tinyint(1) DEFAULT 0,
  `fk_agendamento_exame` int(11) NOT NULL,
  `fk_agendamento_paciente` int(11) NOT NULL,
  `fk_agendamento_medico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `consulta`
--

CREATE TABLE `consulta` (
  `id_consulta` int(11) NOT NULL,
  `data_hora` datetime NOT NULL,
  `realizada` tinyint(1) DEFAULT 0,
  `fk_consulta_paciente` int(11) NOT NULL,
  `fk_consulta_medico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco`
--

CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `estado` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `endereco`
--

INSERT INTO `endereco` (`id_endereco`, `numero`, `logradouro`, `bairro`, `cidade`, `estado`) VALUES
(3, '123', 'Rua Exemplo', 'Bairro A', 'Cidade B', 'PR'),
(4, '123', 'Rua Atualizada', 'Bairro X', 'Cidade Y', 'SP'),
(5, '123', 'Rua Teste', 'Bairro Teste', 'Cidade Teste', 'TT'),
(7, '123', 'Rua Exemplo', 'Bairro A', 'Cidade B', 'PR'),
(8, '123', 'Rua Atualizada', 'Bairro X', 'Cidade Y', 'SP'),
(9, '123', 'Rua Teste', 'Bairro Teste', 'Cidade Teste', 'TT');

-- --------------------------------------------------------

--
-- Estrutura para tabela `especialidade`
--

CREATE TABLE `especialidade` (
  `id_especialidade` int(11) NOT NULL,
  `nome_especialidade` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `exame`
--

CREATE TABLE `exame` (
  `id_exame` int(11) NOT NULL,
  `nome_exame` varchar(100) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `orientacoes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `medico`
--

CREATE TABLE `medico` (
  `id_medico` int(11) NOT NULL,
  `crm` varchar(20) NOT NULL,
  `nome_medico` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `fk_medico_endereco` int(11) DEFAULT NULL,
  `fk_medico_especialidade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `paciente`
--

CREATE TABLE `paciente` (
  `id_paciente` int(11) NOT NULL,
  `nome_paciente` varchar(100) NOT NULL,
  `foto_paciente` varchar(255) NOT NULL,
  `data_nascimento` date NOT NULL,
  `sexo` char(1) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `forma_pagamento` varchar(50) NOT NULL,
  `fk_paciente_endereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `agendamento`
--
ALTER TABLE `agendamento`
  ADD PRIMARY KEY (`id_agendamento_exame`),
  ADD KEY `fk_agendamento_para_exame` (`fk_agendamento_exame`),
  ADD KEY `fk_agendamento_para_paciente` (`fk_agendamento_paciente`),
  ADD KEY `fk_agendamento_medico_valid` (`fk_agendamento_medico`);

--
-- Índices de tabela `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id_consulta`),
  ADD KEY `fk_consulta_tem_paciente` (`fk_consulta_paciente`),
  ADD KEY `fk_consulta_medico` (`fk_consulta_medico`);

--
-- Índices de tabela `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id_endereco`);

--
-- Índices de tabela `especialidade`
--
ALTER TABLE `especialidade`
  ADD PRIMARY KEY (`id_especialidade`);

--
-- Índices de tabela `exame`
--
ALTER TABLE `exame`
  ADD PRIMARY KEY (`id_exame`);

--
-- Índices de tabela `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`id_medico`),
  ADD KEY `fk_medico_endereco` (`fk_medico_endereco`),
  ADD KEY `fk_medico_especialidade` (`fk_medico_especialidade`);

--
-- Índices de tabela `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id_paciente`),
  ADD KEY `fk_paciente_endereco` (`fk_paciente_endereco`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agendamento`
--
ALTER TABLE `agendamento`
  MODIFY `id_agendamento_exame` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `consulta`
--
ALTER TABLE `consulta`
  MODIFY `id_consulta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `especialidade`
--
ALTER TABLE `especialidade`
  MODIFY `id_especialidade` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `exame`
--
ALTER TABLE `exame`
  MODIFY `id_exame` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `medico`
--
ALTER TABLE `medico`
  MODIFY `id_medico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id_paciente` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `agendamento`
--
ALTER TABLE `agendamento`
  ADD CONSTRAINT `fk_agendamento_medico` FOREIGN KEY (`fk_agendamento_medico`) REFERENCES `medico` (`id_medico`),
  ADD CONSTRAINT `fk_agendamento_para_exame` FOREIGN KEY (`fk_agendamento_exame`) REFERENCES `exame` (`id_exame`),
  ADD CONSTRAINT `fk_agendamento_para_paciente` FOREIGN KEY (`fk_agendamento_paciente`) REFERENCES `paciente` (`id_paciente`);

--
-- Restrições para tabelas `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `fk_consulta_medico` FOREIGN KEY (`fk_consulta_medico`) REFERENCES `medico` (`id_medico`),
  ADD CONSTRAINT `fk_consulta_tem_paciente` FOREIGN KEY (`fk_consulta_paciente`) REFERENCES `paciente` (`id_paciente`);

--
-- Restrições para tabelas `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `fk_medico_endereco` FOREIGN KEY (`fk_medico_endereco`) REFERENCES `endereco` (`id_endereco`),
  ADD CONSTRAINT `fk_medico_especialidade` FOREIGN KEY (`fk_medico_especialidade`) REFERENCES `especialidade` (`id_especialidade`);

--
-- Restrições para tabelas `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `fk_paciente_endereco` FOREIGN KEY (`fk_paciente_endereco`) REFERENCES `endereco` (`id_endereco`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
