# backend: description

- application microservices-base to manage: employees, addresses, projects and companies
	- each employee is associated an address
	- each project is assigned an employee and a projcet is destined to a company
- the backend application is implemented ***microservices base*** architecture
- each business microservice is implemented into ***clean architecture*** pattern

- main framework used:
	- spring and spring boot

- business microservices (bs-ms) of the application:

	- ***k8s-clean-archi-bs-ms-address***
	- ***k8s-clean-archi-bs-ms-employee***
	- ***k8s-clean-archi-bs-ms-project***
	- ***k8s-clean-archi-bs-ms-company***

- utility microservices:
	- ***k8s-ms-config-service***: a config server to expose configurations to services
	- ***k8s-gateway-service***: a gateway between the back and front

- each microservice gets its configuration properties from: 
	- a spring cloud configuration server: ***k8s-ms-config-service***
	- the config server pulls the needed configurations from a git center of all configuration files [here](https://github.com/placidenduwayo1/config-files-center.git)
	
- **(1)** microservices communicate each other; **(2)** management of alternative scenarios and resilience, **(3)** service discovery,load balancing and configuration
	- (1) **spring cloud openfeign**
	- (2) spring cloud circuit breaker: **Resilience4J**
	- (3) **spring cloud kubernetes** dependecy to permit microservices to be discovered, load balanced and configured

# microservices containerization

- each microservice is containerized using **Docker Engine**
- all microservices are deployed using a template file: **docker compose**: [images deploy](https://github.com/placidenduwayo1/K8s-AEPC-Docker-Deploy.git)

# containers orchestration in K8s cluster
- all docker containers of microservices of the application are deployed and orchestrated into k8s cluster
	- to get start with containers orchestration with k8s: [doc](https://kubernetes.io/fr/docs/home/)

- each microservice container is deployed into  ***k8s pod***
	- each microservice container is deployed into **3 replica pods**
- a ***k8s service*** is created to expose the pods in deployment outside

here is the git of deploying all microservices container into kubernetes cluster: [containers-orchestr](https://github.com/placidenduwayo1/K8s-AEPC-Containers-Deploy.git)

## prerequisite:
- to orchestrate containers with Kubernetes in local, you need **Minikube** and **Kubectl** running.
- Minikube installation create a one node cluster to have Master and Worker Nodes on the same computer. 
- you can create more than one node cluster as follows: 
	- >```minikube --nodes nb-of-node --profile profile-flag start```
	- >```minikube --nodes 3 --profile nodes start```: 
		- the commande line create a cluster with 3 nodes
		- one of the three node is a Master Node et the 2 others are Worker Nodes
- Kubectl is a Kubernets CLI to make developer to communicate with the Master Node

here is the documentations about those two tools: [Minikube](https://kubernetes.io/fr/docs/setup/learning-environment/minikube/) and [Kubectl](https://kubernetes.io/fr/docs/tasks/tools/install-kubectl/)

# architecture
the gloabal and detailed architecture of project can be viewed [here](https://drive.google.com/file/d/1bedn0GuPzPgybFJBWTytlyV3dxl4WJiL/view?usp=drive_link)
