SUMMARY = "Package wrapper around a minimal Xen guest image"
# This is a lie, see the license manifest inside the xen guest image
LICENSE="CLOSED"

inherit xen-guest-package

SRC_URI = "file://xen-guest-image-minimal.cfg"

do_install:append() {
  install ${S}/../xen-guest-image-minimal.cfg ${D}${vesseldir}/
}
