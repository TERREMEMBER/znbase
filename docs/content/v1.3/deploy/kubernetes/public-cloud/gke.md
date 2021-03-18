
```sh
$ helm install ZNbase -f expose-all.yaml --namespace yb-demo --name yb-demo \
  --set annotations.tserver.loadbalancer."cloud\.google\.com/load-balancer-type"=Internal --wait
```
