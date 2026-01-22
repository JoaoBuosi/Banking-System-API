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
  default = "root"
}

variable "db_password" {
  description = "Senha do banco"
  type        = string
  sensitive   = true
}
