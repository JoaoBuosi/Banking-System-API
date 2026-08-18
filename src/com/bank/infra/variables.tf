variable "aws_region" {
  description = "Região da AWS"
  type        = string
  default     = "us-east-1"
}

variable "instance_type" {
  description = "Tipo da EC2 (Free Tier)"
  type        = string
  default     = "t2.micro"
}

variable "db_name" {
  default = "bankdb"
}

variable "db_user" {
  description = "Usuário do banco (não use root)"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "Senha do banco"
  type        = string
  sensitive   = true
}

variable "my_ip" {
  description = "Seu IP público, para acesso SSH restrito (formato: 1.2.3.4/32)"
  type        = string
}
