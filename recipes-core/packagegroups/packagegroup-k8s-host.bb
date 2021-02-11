SUMMARY = "Provides a set of tools for hosting k8s and container guests"

inherit packagegroup

RDEPENDS_${PN} = "\
    ca-certificates \
    conntrack-tools \
    cri-o \
    crictl \
    kernel-module-configs \
    kubernetes \
    podman \
    tzdata \
    virtual/containerd \
    virtual/runc \
    "
